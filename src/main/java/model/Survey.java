/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePle.model;
import java.sql.Date;
import java.util.*;

// line 20 "../../../../../TreePle.ump"
public class Survey
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum StatusEnum { Diseased, CutDown, MarkedCutDown, Planted }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Survey Attributes
  private Date date;
  private StatusEnum status;

  //Survey Associations
  private Tree trees;
  private List<SustainabilityAttribute> sustainabilityAtributes;
  private LocalResident localResident;
  private Scientist user;
  private TreePLE treePLE;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Survey(Date aDate, StatusEnum aStatus, Tree aTrees, Scientist aUser, TreePLE aTreePLE)
  {
    date = aDate;
    status = aStatus;
    boolean didAddTrees = setTrees(aTrees);
    if (!didAddTrees)
    {
      throw new RuntimeException("Unable to create survey due to trees");
    }
    sustainabilityAtributes = new ArrayList<SustainabilityAttribute>();
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create survey due to user");
    }
    boolean didAddTreePLE = setTreePLE(aTreePLE);
    if (!didAddTreePLE)
    {
      throw new RuntimeException("Unable to create survey due to treePLE");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setStatus(StatusEnum aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public StatusEnum getStatus()
  {
    return status;
  }

  public Tree getTrees()
  {
    return trees;
  }

  public SustainabilityAttribute getSustainabilityAtribute(int index)
  {
    SustainabilityAttribute aSustainabilityAtribute = sustainabilityAtributes.get(index);
    return aSustainabilityAtribute;
  }

  public List<SustainabilityAttribute> getSustainabilityAtributes()
  {
    List<SustainabilityAttribute> newSustainabilityAtributes = Collections.unmodifiableList(sustainabilityAtributes);
    return newSustainabilityAtributes;
  }

  public int numberOfSustainabilityAtributes()
  {
    int number = sustainabilityAtributes.size();
    return number;
  }

  public boolean hasSustainabilityAtributes()
  {
    boolean has = sustainabilityAtributes.size() > 0;
    return has;
  }

  public int indexOfSustainabilityAtribute(SustainabilityAttribute aSustainabilityAtribute)
  {
    int index = sustainabilityAtributes.indexOf(aSustainabilityAtribute);
    return index;
  }

  public LocalResident getLocalResident()
  {
    return localResident;
  }

  public boolean hasLocalResident()
  {
    boolean has = localResident != null;
    return has;
  }

  public Scientist getUser()
  {
    return user;
  }

  public TreePLE getTreePLE()
  {
    return treePLE;
  }

  public boolean setTrees(Tree aTrees)
  {
    boolean wasSet = false;
    if (aTrees == null)
    {
      return wasSet;
    }

    Tree existingTrees = trees;
    trees = aTrees;
    if (existingTrees != null && !existingTrees.equals(aTrees))
    {
      existingTrees.removeSurvey(this);
    }
    trees.addSurvey(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfSustainabilityAtributes()
  {
    return 0;
  }

  public SustainabilityAttribute addSustainabilityAtribute(double aIndex, TreePLE aTreePLE)
  {
    return new SustainabilityAttribute(aIndex, aTreePLE, this);
  }

  public boolean addSustainabilityAtribute(SustainabilityAttribute aSustainabilityAtribute)
  {
    boolean wasAdded = false;
    if (sustainabilityAtributes.contains(aSustainabilityAtribute)) { return false; }
    Survey existingSurvey = aSustainabilityAtribute.getSurvey();
    boolean isNewSurvey = existingSurvey != null && !this.equals(existingSurvey);
    if (isNewSurvey)
    {
      aSustainabilityAtribute.setSurvey(this);
    }
    else
    {
      sustainabilityAtributes.add(aSustainabilityAtribute);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSustainabilityAtribute(SustainabilityAttribute aSustainabilityAtribute)
  {
    boolean wasRemoved = false;
    //Unable to remove aSustainabilityAtribute, as it must always have a survey
    if (!this.equals(aSustainabilityAtribute.getSurvey()))
    {
      sustainabilityAtributes.remove(aSustainabilityAtribute);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSustainabilityAtributeAt(SustainabilityAttribute aSustainabilityAtribute, int index)
  {  
    boolean wasAdded = false;
    if(addSustainabilityAtribute(aSustainabilityAtribute))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSustainabilityAtributes()) { index = numberOfSustainabilityAtributes() - 1; }
      sustainabilityAtributes.remove(aSustainabilityAtribute);
      sustainabilityAtributes.add(index, aSustainabilityAtribute);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSustainabilityAtributeAt(SustainabilityAttribute aSustainabilityAtribute, int index)
  {
    boolean wasAdded = false;
    if(sustainabilityAtributes.contains(aSustainabilityAtribute))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSustainabilityAtributes()) { index = numberOfSustainabilityAtributes() - 1; }
      sustainabilityAtributes.remove(aSustainabilityAtribute);
      sustainabilityAtributes.add(index, aSustainabilityAtribute);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSustainabilityAtributeAt(aSustainabilityAtribute, index);
    }
    return wasAdded;
  }

  public boolean setLocalResident(LocalResident aLocalResident)
  {
    boolean wasSet = false;
    LocalResident existingLocalResident = localResident;
    localResident = aLocalResident;
    if (existingLocalResident != null && !existingLocalResident.equals(aLocalResident))
    {
      existingLocalResident.removeSurvey(this);
    }
    if (aLocalResident != null)
    {
      aLocalResident.addSurvey(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setUser(Scientist aUser)
  {
    boolean wasSet = false;
    if (aUser == null)
    {
      return wasSet;
    }

    Scientist existingUser = user;
    user = aUser;
    if (existingUser != null && !existingUser.equals(aUser))
    {
      existingUser.removeSurvey(this);
    }
    user.addSurvey(this);
    wasSet = true;
    return wasSet;
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
      existingTreePLE.removeSurvey(this);
    }
    treePLE.addSurvey(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Tree placeholderTrees = trees;
    this.trees = null;
    placeholderTrees.removeSurvey(this);
    for(int i=sustainabilityAtributes.size(); i > 0; i--)
    {
      SustainabilityAttribute aSustainabilityAtribute = sustainabilityAtributes.get(i - 1);
      aSustainabilityAtribute.delete();
    }
    if (localResident != null)
    {
      LocalResident placeholderLocalResident = localResident;
      this.localResident = null;
      placeholderLocalResident.removeSurvey(this);
    }
    Scientist placeholderUser = user;
    this.user = null;
    placeholderUser.removeSurvey(this);
    TreePLE placeholderTreePLE = treePLE;
    this.treePLE = null;
    placeholderTreePLE.removeSurvey(this);
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "trees = "+(getTrees()!=null?Integer.toHexString(System.identityHashCode(getTrees())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "localResident = "+(getLocalResident()!=null?Integer.toHexString(System.identityHashCode(getLocalResident())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "treePLE = "+(getTreePLE()!=null?Integer.toHexString(System.identityHashCode(getTreePLE())):"null");
  }
}