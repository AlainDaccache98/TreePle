/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePle.model;
import java.util.*;

// line 31 "../../../../../TreePle.ump"
public class LocalResident extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //LocalResident Associations
  private List<Property> properties;
  private List<Survey> surveys;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public LocalResident(String aName, String aEmail, String aUserID, TreePLE aTreePLE, Property... allProperties)
  {
    super(aName, aEmail, aUserID, aTreePLE);
    properties = new ArrayList<Property>();
    boolean didAddProperties = setProperties(allProperties);
    if (!didAddProperties)
    {
      throw new RuntimeException("Unable to create LocalResident, must have at least 1 properties");
    }
    surveys = new ArrayList<Survey>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Property getProperty(int index)
  {
    Property aProperty = properties.get(index);
    return aProperty;
  }

  public List<Property> getProperties()
  {
    List<Property> newProperties = Collections.unmodifiableList(properties);
    return newProperties;
  }

  public int numberOfProperties()
  {
    int number = properties.size();
    return number;
  }

  public boolean hasProperties()
  {
    boolean has = properties.size() > 0;
    return has;
  }

  public int indexOfProperty(Property aProperty)
  {
    int index = properties.indexOf(aProperty);
    return index;
  }

  public Survey getSurvey(int index)
  {
    Survey aSurvey = surveys.get(index);
    return aSurvey;
  }

  public List<Survey> getSurveys()
  {
    List<Survey> newSurveys = Collections.unmodifiableList(surveys);
    return newSurveys;
  }

  public int numberOfSurveys()
  {
    int number = surveys.size();
    return number;
  }

  public boolean hasSurveys()
  {
    boolean has = surveys.size() > 0;
    return has;
  }

  public int indexOfSurvey(Survey aSurvey)
  {
    int index = surveys.indexOf(aSurvey);
    return index;
  }

  public boolean isNumberOfPropertiesValid()
  {
    boolean isValid = numberOfProperties() >= minimumNumberOfProperties();
    return isValid;
  }

  public static int minimumNumberOfProperties()
  {
    return 1;
  }

  public boolean addProperty(Property aProperty)
  {
    boolean wasAdded = false;
    if (properties.contains(aProperty)) { return false; }
    properties.add(aProperty);
    if (aProperty.indexOfLocalResident(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aProperty.addLocalResident(this);
      if (!wasAdded)
      {
        properties.remove(aProperty);
      }
    }
    return wasAdded;
  }

  public boolean removeProperty(Property aProperty)
  {
    boolean wasRemoved = false;
    if (!properties.contains(aProperty))
    {
      return wasRemoved;
    }

    if (numberOfProperties() <= minimumNumberOfProperties())
    {
      return wasRemoved;
    }

    int oldIndex = properties.indexOf(aProperty);
    properties.remove(oldIndex);
    if (aProperty.indexOfLocalResident(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aProperty.removeLocalResident(this);
      if (!wasRemoved)
      {
        properties.add(oldIndex,aProperty);
      }
    }
    return wasRemoved;
  }

  public boolean setProperties(Property... newProperties)
  {
    boolean wasSet = false;
    ArrayList<Property> verifiedProperties = new ArrayList<Property>();
    for (Property aProperty : newProperties)
    {
      if (verifiedProperties.contains(aProperty))
      {
        continue;
      }
      verifiedProperties.add(aProperty);
    }

    if (verifiedProperties.size() != newProperties.length || verifiedProperties.size() < minimumNumberOfProperties())
    {
      return wasSet;
    }

    ArrayList<Property> oldProperties = new ArrayList<Property>(properties);
    properties.clear();
    for (Property aNewProperty : verifiedProperties)
    {
      properties.add(aNewProperty);
      if (oldProperties.contains(aNewProperty))
      {
        oldProperties.remove(aNewProperty);
      }
      else
      {
        aNewProperty.addLocalResident(this);
      }
    }

    for (Property anOldProperty : oldProperties)
    {
      anOldProperty.removeLocalResident(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addPropertyAt(Property aProperty, int index)
  {  
    boolean wasAdded = false;
    if(addProperty(aProperty))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProperties()) { index = numberOfProperties() - 1; }
      properties.remove(aProperty);
      properties.add(index, aProperty);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePropertyAt(Property aProperty, int index)
  {
    boolean wasAdded = false;
    if(properties.contains(aProperty))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProperties()) { index = numberOfProperties() - 1; }
      properties.remove(aProperty);
      properties.add(index, aProperty);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPropertyAt(aProperty, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSurveys()
  {
    return 0;
  }

  public boolean addSurvey(Survey aSurvey)
  {
    boolean wasAdded = false;
    if (surveys.contains(aSurvey)) { return false; }
    LocalResident existingLocalResident = aSurvey.getLocalResident();
    if (existingLocalResident == null)
    {
      aSurvey.setLocalResident(this);
    }
    else if (!this.equals(existingLocalResident))
    {
      existingLocalResident.removeSurvey(aSurvey);
      addSurvey(aSurvey);
    }
    else
    {
      surveys.add(aSurvey);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSurvey(Survey aSurvey)
  {
    boolean wasRemoved = false;
    if (surveys.contains(aSurvey))
    {
      surveys.remove(aSurvey);
      aSurvey.setLocalResident(null);
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
      if(index > numberOfSurveys()) { index = numberOfSurveys() - 1; }
      surveys.remove(aSurvey);
      surveys.add(index, aSurvey);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSurveyAt(Survey aSurvey, int index)
  {
    boolean wasAdded = false;
    if(surveys.contains(aSurvey))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSurveys()) { index = numberOfSurveys() - 1; }
      surveys.remove(aSurvey);
      surveys.add(index, aSurvey);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSurveyAt(aSurvey, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Property> copyOfProperties = new ArrayList<Property>(properties);
    properties.clear();
    for(Property aProperty : copyOfProperties)
    {
      aProperty.removeLocalResident(this);
    }
    while( !surveys.isEmpty() )
    {
      surveys.get(0).setLocalResident(null);
    }
    super.delete();
  }

}