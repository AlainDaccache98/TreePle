/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePle.model;
import java.util.*;

// line 75 "../../../../../TreePle.ump"
public class Property
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Property Attributes
  private float length;
  private float width;

  //Property Associations
  private List<LocalResident> localResidents;
  private Location location;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Property(float aLength, float aWidth, Location aLocation)
  {
    length = aLength;
    width = aWidth;
    localResidents = new ArrayList<LocalResident>();
    boolean didAddLocation = setLocation(aLocation);
    if (!didAddLocation)
    {
      throw new RuntimeException("Unable to create property due to location");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLength(float aLength)
  {
    boolean wasSet = false;
    length = aLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setWidth(float aWidth)
  {
    boolean wasSet = false;
    width = aWidth;
    wasSet = true;
    return wasSet;
  }

  public float getLength()
  {
    return length;
  }

  public float getWidth()
  {
    return width;
  }

  public LocalResident getLocalResident(int index)
  {
    LocalResident aLocalResident = localResidents.get(index);
    return aLocalResident;
  }

  public List<LocalResident> getLocalResidents()
  {
    List<LocalResident> newLocalResidents = Collections.unmodifiableList(localResidents);
    return newLocalResidents;
  }

  public int numberOfLocalResidents()
  {
    int number = localResidents.size();
    return number;
  }

  public boolean hasLocalResidents()
  {
    boolean has = localResidents.size() > 0;
    return has;
  }

  public int indexOfLocalResident(LocalResident aLocalResident)
  {
    int index = localResidents.indexOf(aLocalResident);
    return index;
  }

  public Location getLocation()
  {
    return location;
  }

  public static int minimumNumberOfLocalResidents()
  {
    return 0;
  }

  public boolean addLocalResident(LocalResident aLocalResident)
  {
    boolean wasAdded = false;
    if (localResidents.contains(aLocalResident)) { return false; }
    localResidents.add(aLocalResident);
    if (aLocalResident.indexOfProperty(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aLocalResident.addProperty(this);
      if (!wasAdded)
      {
        localResidents.remove(aLocalResident);
      }
    }
    return wasAdded;
  }

  public boolean removeLocalResident(LocalResident aLocalResident)
  {
    boolean wasRemoved = false;
    if (!localResidents.contains(aLocalResident))
    {
      return wasRemoved;
    }

    int oldIndex = localResidents.indexOf(aLocalResident);
    localResidents.remove(oldIndex);
    if (aLocalResident.indexOfProperty(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aLocalResident.removeProperty(this);
      if (!wasRemoved)
      {
        localResidents.add(oldIndex,aLocalResident);
      }
    }
    return wasRemoved;
  }

  public boolean addLocalResidentAt(LocalResident aLocalResident, int index)
  {  
    boolean wasAdded = false;
    if(addLocalResident(aLocalResident))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLocalResidents()) { index = numberOfLocalResidents() - 1; }
      localResidents.remove(aLocalResident);
      localResidents.add(index, aLocalResident);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLocalResidentAt(LocalResident aLocalResident, int index)
  {
    boolean wasAdded = false;
    if(localResidents.contains(aLocalResident))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLocalResidents()) { index = numberOfLocalResidents() - 1; }
      localResidents.remove(aLocalResident);
      localResidents.add(index, aLocalResident);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLocalResidentAt(aLocalResident, index);
    }
    return wasAdded;
  }

  public boolean setLocation(Location aNewLocation)
  {
    boolean wasSet = false;
    if (aNewLocation == null)
    {
      //Unable to setLocation to null, as property must always be associated to a location
      return wasSet;
    }
    
    Property existingProperty = aNewLocation.getProperty();
    if (existingProperty != null && !equals(existingProperty))
    {
      //Unable to setLocation, the current location already has a property, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Location anOldLocation = location;
    location = aNewLocation;
    location.setProperty(this);

    if (anOldLocation != null)
    {
      anOldLocation.setProperty(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<LocalResident> copyOfLocalResidents = new ArrayList<LocalResident>(localResidents);
    localResidents.clear();
    for(LocalResident aLocalResident : copyOfLocalResidents)
    {
      if (aLocalResident.numberOfProperties() <= LocalResident.minimumNumberOfProperties())
      {
        aLocalResident.delete();
      }
      else
      {
        aLocalResident.removeProperty(this);
      }
    }
    Location existingLocation = location;
    location = null;
    if (existingLocation != null)
    {
      existingLocation.setProperty(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "length" + ":" + getLength()+ "," +
            "width" + ":" + getWidth()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "location = "+(getLocation()!=null?Integer.toHexString(System.identityHashCode(getLocation())):"null");
  }
}