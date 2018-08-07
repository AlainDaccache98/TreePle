/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePle.model;
import java.sql.Date;
import java.util.*;

import ca.mcgill.ecse321.TreePle.model.Survey.StatusEnum;

// line 7 "../../../../../TreePle.ump"
public class Tree
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum SpeciesEnum { EnglishOak, Pecan, Walnut, Willow }
  public enum LandTypeEnum { Residential, Institutional, Park, Municipal }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tree Attributes
  private String municipality;
  private LandTypeEnum landTypes;
  private float id;
  private float height;
  private float diamOfCanopy;
  private Date datePlanted;
  private Date dateModified;
  private SpeciesEnum species;

  //Tree Associations
  private List<Survey> surveies;
  private TreePLE treePLE;
  private Location location;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tree(String aMunicipality, LandTypeEnum aLandTypes, float aId, float aHeight, float aDiamOfCanopy, Date aDatePlanted, Date aDateModified, SpeciesEnum aSpecies, TreePLE aTreePLE, Location aLocation)
  {
    municipality = aMunicipality;
    landTypes = aLandTypes;
    id = aId;
    height = aHeight;
    diamOfCanopy = aDiamOfCanopy;
    datePlanted = aDatePlanted;
    dateModified = aDateModified;
    species = aSpecies;
    surveies = new ArrayList<Survey>();
    boolean didAddTreePLE = setTreePLE(aTreePLE);
    if (!didAddTreePLE)
    {
      throw new RuntimeException("Unable to create tree due to treePLE");
    }
    boolean didAddLocation = setLocation(aLocation);
    if (!didAddLocation)
    {
      throw new RuntimeException("Unable to create tree due to location");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMunicipality(String aMunicipality)
  {
    boolean wasSet = false;
    municipality = aMunicipality;
    wasSet = true;
    return wasSet;
  }

  public boolean setLandTypes(LandTypeEnum aLandTypes)
  {
    boolean wasSet = false;
    landTypes = aLandTypes;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(float aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setHeight(float aHeight)
  {
    boolean wasSet = false;
    height = aHeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setDiamOfCanopy(float aDiamOfCanopy)
  {
    boolean wasSet = false;
    diamOfCanopy = aDiamOfCanopy;
    wasSet = true;
    return wasSet;
  }

  public boolean setDatePlanted(Date aDatePlanted)
  {
    boolean wasSet = false;
    datePlanted = aDatePlanted;
    wasSet = true;
    return wasSet;
  }

  public boolean setDateModified(Date aDateModified)
  {
    boolean wasSet = false;
    dateModified = aDateModified;
    wasSet = true;
    return wasSet;
  }

  public boolean setSpecies(SpeciesEnum aSpecies)
  {
    boolean wasSet = false;
    species = aSpecies;
    wasSet = true;
    return wasSet;
  }

  public String getMunicipality()
  {
    return municipality;
  }

  public LandTypeEnum getLandTypes()
  {
    return landTypes;
  }

  public float getId()
  {
    return id;
  }

  public float getHeight()
  {
    return height;
  }

  public float getDiamOfCanopy()
  {
    return diamOfCanopy;
  }

  public Date getDatePlanted()
  {
    return datePlanted;
  }

  public Date getDateModified()
  {
    return dateModified;
  }

  public SpeciesEnum getSpecies()
  {
    return species;
  }

  public Survey getSurvey(int index)
  {
    Survey aSurvey = surveies.get(index);
    return aSurvey;
  }

  public List<Survey> getSurveies()
  {
    List<Survey> newSurveies = Collections.unmodifiableList(surveies);
    return newSurveies;
  }

  public int numberOfSurveies()
  {
    int number = surveies.size();
    return number;
  }

  public boolean hasSurveies()
  {
    boolean has = surveies.size() > 0;
    return has;
  }

  public int indexOfSurvey(Survey aSurvey)
  {
    int index = surveies.indexOf(aSurvey);
    return index;
  }

  public TreePLE getTreePLE()
  {
    return treePLE;
  }

  public Location getLocation()
  {
    return location;
  }

  public static int minimumNumberOfSurveies()
  {
    return 0;
  }

  public Survey addSurvey(Date aDate, StatusEnum aStatus, Scientist aUser, TreePLE aTreePLE)
  {
    return new Survey(aDate, aStatus, this, aUser, aTreePLE);
  }

  public boolean addSurvey(Survey aSurvey)
  {
    boolean wasAdded = false;
    if (surveies.contains(aSurvey)) { return false; }
    Tree existingTrees = aSurvey.getTrees();
    boolean isNewTrees = existingTrees != null && !this.equals(existingTrees);
    if (isNewTrees)
    {
      aSurvey.setTrees(this);
    }
    else
    {
      surveies.add(aSurvey);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSurvey(Survey aSurvey)
  {
    boolean wasRemoved = false;
    //Unable to remove aSurvey, as it must always have a trees
    if (!this.equals(aSurvey.getTrees()))
    {
      surveies.remove(aSurvey);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSurveyAt(Survey aSurvey, int index)
  {  
    boolean wasAdded = false;
    if(addSurvey(aSurvey))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSurveies()) { index = numberOfSurveies() - 1; }
      surveies.remove(aSurvey);
      surveies.add(index, aSurvey);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSurveyAt(Survey aSurvey, int index)
  {
    boolean wasAdded = false;
    if(surveies.contains(aSurvey))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSurveies()) { index = numberOfSurveies() - 1; }
      surveies.remove(aSurvey);
      surveies.add(index, aSurvey);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSurveyAt(aSurvey, index);
    }
    return wasAdded;
  }

  public boolean setTreePLE(TreePLE aTreePLE)
  {
    boolean wasSet = false;
    if (aTreePLE == null)
    {
      return wasSet;
    }

    TreePLE existingTreePLE = treePLE;
    treePLE = aTreePLE;
    if (existingTreePLE != null && !existingTreePLE.equals(aTreePLE))
    {
      existingTreePLE.removeTree(this);
    }
    treePLE.addTree(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setLocation(Location aNewLocation)
  {
    boolean wasSet = false;
    if (aNewLocation == null)
    {
      //Unable to setLocation to null, as tree must always be associated to a location
      return wasSet;
    }
    
    Tree existingTree = aNewLocation.getTree();
    if (existingTree != null && !equals(existingTree))
    {
      //Unable to setLocation, the current location already has a tree, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Location anOldLocation = location;
    location = aNewLocation;
    location.setTree(this);

    if (anOldLocation != null)
    {
      anOldLocation.setTree(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=surveies.size(); i > 0; i--)
    {
      Survey aSurvey = surveies.get(i - 1);
      aSurvey.delete();
    }
    TreePLE placeholderTreePLE = treePLE;
    this.treePLE = null;
    placeholderTreePLE.removeTree(this);
    Location existingLocation = location;
    location = null;
    if (existingLocation != null)
    {
      existingLocation.setTree(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "municipality" + ":" + getMunicipality()+ "," +
            "id" + ":" + getId()+ "," +
            "height" + ":" + getHeight()+ "," +
            "diamOfCanopy" + ":" + getDiamOfCanopy()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "landTypes" + "=" + (getLandTypes() != null ? !getLandTypes().equals(this)  ? getLandTypes().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "datePlanted" + "=" + (getDatePlanted() != null ? !getDatePlanted().equals(this)  ? getDatePlanted().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "dateModified" + "=" + (getDateModified() != null ? !getDateModified().equals(this)  ? getDateModified().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "species" + "=" + (getSpecies() != null ? !getSpecies().equals(this)  ? getSpecies().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "treePLE = "+(getTreePLE()!=null?Integer.toHexString(System.identityHashCode(getTreePLE())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "location = "+(getLocation()!=null?Integer.toHexString(System.identityHashCode(getLocation())):"null");
  }
}