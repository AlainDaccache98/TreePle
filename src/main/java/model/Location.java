/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePle.model;
import java.sql.Date;

// line 53 "../../../../../TreePle.ump"
public class Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Location Attributes
  private float latitude;
  private float longitude;

  //Location Associations
  private Tree tree;
  private Property property;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Location(float aLatitude, float aLongitude)
  {
    latitude = aLatitude;
    longitude = aLongitude;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLatitude(float aLatitude)
  {
    boolean wasSet = false;
    latitude = aLatitude;
    wasSet = true;
    return wasSet;
  }

  public boolean setLongitude(float aLongitude)
  {
    boolean wasSet = false;
    longitude = aLongitude;
    wasSet = true;
    return wasSet;
  }

  public float getLatitude()
  {
    return latitude;
  }

  public float getLongitude()
  {
    return longitude;
  }

  public Tree getTree()
  {
    return tree;
  }

  public boolean hasTree()
  {
    boolean has = tree != null;
    return has;
  }

  public Property getProperty()
  {
    return property;
  }

  public boolean hasProperty()
  {
    boolean has = property != null;
    return has;
  }

  public boolean setTree(Tree aNewTree)
  {
    boolean wasSet = false;
    if (tree != null && !tree.equals(aNewTree) && equals(tree.getLocation()))
    {
      //Unable to setTree, as existing tree would become an orphan
      return wasSet;
    }

    tree = aNewTree;
    Location anOldLocation = aNewTree != null ? aNewTree.getLocation() : null;

    if (!this.equals(anOldLocation))
    {
      if (anOldLocation != null)
      {
        anOldLocation.tree = null;
      }
      if (tree != null)
      {
        tree.setLocation(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public boolean setProperty(Property aNewProperty)
  {
    boolean wasSet = false;
    if (property != null && !property.equals(aNewProperty) && equals(property.getLocation()))
    {
      //Unable to setProperty, as existing property would become an orphan
      return wasSet;
    }

    property = aNewProperty;
    Location anOldLocation = aNewProperty != null ? aNewProperty.getLocation() : null;

    if (!this.equals(anOldLocation))
    {
      if (anOldLocation != null)
      {
        anOldLocation.property = null;
      }
      if (property != null)
      {
        property.setLocation(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Tree existingTree = tree;
    tree = null;
    if (existingTree != null)
    {
      existingTree.delete();
    }
    Property existingProperty = property;
    property = null;
    if (existingProperty != null)
    {
      existingProperty.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "latitude" + ":" + getLatitude()+ "," +
            "longitude" + ":" + getLongitude()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tree = "+(getTree()!=null?Integer.toHexString(System.identityHashCode(getTree())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "property = "+(getProperty()!=null?Integer.toHexString(System.identityHashCode(getProperty())):"null");
  }
}