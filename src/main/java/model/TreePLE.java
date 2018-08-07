/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePle.model;
import java.util.*;

import ca.mcgill.ecse321.TreePle.model.Survey.StatusEnum;
import ca.mcgill.ecse321.TreePle.model.Tree.LandTypeEnum;
import ca.mcgill.ecse321.TreePle.model.Tree.SpeciesEnum;

import java.sql.Date;

// line 46 "../../../../../TreePle.ump"
public class TreePLE
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TreePLE Associations
  private List<Tree> trees;
  private List<Survey> surveys;
  private List<User> user;
  private List<Forecast> forecasts;
  private List<SustainabilityAttribute> sustainabilityAttributes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TreePLE()
  {
    trees = new ArrayList<Tree>();
    surveys = new ArrayList<Survey>();
    user = new ArrayList<User>();
    forecasts = new ArrayList<Forecast>();
    sustainabilityAttributes = new ArrayList<SustainabilityAttribute>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Tree getTree(int index)
  {
    Tree aTree = trees.get(index);
    return aTree;
  }

  public List<Tree> getTrees()
  {
    List<Tree> newTrees = Collections.unmodifiableList(trees);
    return newTrees;
  }

  public int numberOfTrees()
  {
    int number = trees.size();
    return number;
  }

  public boolean hasTrees()
  {
    boolean has = trees.size() > 0;
    return has;
  }

  public int indexOfTree(Tree aTree)
  {
    int index = trees.indexOf(aTree);
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

  public User getUser(int index)
  {
    User aUser = user.get(index);
    return aUser;
  }

  public List<User> getUser()
  {
    List<User> newUser = Collections.unmodifiableList(user);
    return newUser;
  }

  public int numberOfUser()
  {
    int number = user.size();
    return number;
  }

  public boolean hasUser()
  {
    boolean has = user.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = user.indexOf(aUser);
    return index;
  }

  public Forecast getForecast(int index)
  {
    Forecast aForecast = forecasts.get(index);
    return aForecast;
  }

  public List<Forecast> getForecasts()
  {
    List<Forecast> newForecasts = Collections.unmodifiableList(forecasts);
    return newForecasts;
  }

  public int numberOfForecasts()
  {
    int number = forecasts.size();
    return number;
  }

  public boolean hasForecasts()
  {
    boolean has = forecasts.size() > 0;
    return has;
  }

  public int indexOfForecast(Forecast aForecast)
  {
    int index = forecasts.indexOf(aForecast);
    return index;
  }

  public SustainabilityAttribute getSustainabilityAttribute(int index)
  {
    SustainabilityAttribute aSustainabilityAttribute = sustainabilityAttributes.get(index);
    return aSustainabilityAttribute;
  }

  public List<SustainabilityAttribute> getSustainabilityAttributes()
  {
    List<SustainabilityAttribute> newSustainabilityAttributes = Collections.unmodifiableList(sustainabilityAttributes);
    return newSustainabilityAttributes;
  }

  public int numberOfSustainabilityAttributes()
  {
    int number = sustainabilityAttributes.size();
    return number;
  }

  public boolean hasSustainabilityAttributes()
  {
    boolean has = sustainabilityAttributes.size() > 0;
    return has;
  }

  public int indexOfSustainabilityAttribute(SustainabilityAttribute aSustainabilityAttribute)
  {
    int index = sustainabilityAttributes.indexOf(aSustainabilityAttribute);
    return index;
  }

  public static int minimumNumberOfTrees()
  {
    return 0;
  }

  public Tree addTree(String aMunicipality, LandTypeEnum aLandTypes, float aId, float aHeight, float aDiamOfCanopy, Date aDatePlanted, Date aDateModified, SpeciesEnum aSpecies, Location aLocation)
  {
    return new Tree(aMunicipality, aLandTypes, aId, aHeight, aDiamOfCanopy, aDatePlanted, aDateModified, aSpecies, this, aLocation);
  }

  public boolean addTree(Tree aTree)
  {
    boolean wasAdded = false;
    if (trees.contains(aTree)) { return false; }
    TreePLE existingTreePLE = aTree.getTreePLE();
    boolean isNewTreePLE = existingTreePLE != null && !this.equals(existingTreePLE);
    if (isNewTreePLE)
    {
      aTree.setTreePLE(this);
    }
    else
    {
      trees.add(aTree);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTree(Tree aTree)
  {
    boolean wasRemoved = false;
    //Unable to remove aTree, as it must always have a treePLE
    if (!this.equals(aTree.getTreePLE()))
    {
    	System.out.println("shu1");
      trees.remove(aTree);
  	System.out.println("shu2" +       trees.remove(aTree));

      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTreeAt(Tree aTree, int index)
  {  
    boolean wasAdded = false;
    if(addTree(aTree))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTrees()) { index = numberOfTrees() - 1; }
      trees.remove(aTree);
      trees.add(index, aTree);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTreeAt(Tree aTree, int index)
  {
    boolean wasAdded = false;
    if(trees.contains(aTree))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTrees()) { index = numberOfTrees() - 1; }
      trees.remove(aTree);
      trees.add(index, aTree);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTreeAt(aTree, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSurveys()
  {
    return 0;
  }

  public Survey addSurvey(Date aDate, StatusEnum aStatus, Tree aTrees, Scientist aUser)
  {
    return new Survey(aDate, aStatus, aTrees, aUser, this);
  }

  public boolean addSurvey(Survey aSurvey)
  {
    boolean wasAdded = false;
    if (surveys.contains(aSurvey)) { return false; }
    TreePLE existingTreePLE = aSurvey.getTreePLE();
    boolean isNewTreePLE = existingTreePLE != null && !this.equals(existingTreePLE);
    if (isNewTreePLE)
    {
      aSurvey.setTreePLE(this);
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
    //Unable to remove aSurvey, as it must always have a treePLE
    if (!this.equals(aSurvey.getTreePLE()))
    {
      surveys.remove(aSurvey);
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

  public static int minimumNumberOfUser()
  {
    return 0;
  }

  public User addUser(String aName, String aEmail, String aUserID)
  {
    return new User(aName, aEmail, aUserID, this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (user.contains(aUser)) { return false; }
    TreePLE existingTreePLE = aUser.getTreePLE();
    boolean isNewTreePLE = existingTreePLE != null && !this.equals(existingTreePLE);
    if (isNewTreePLE)
    {
      aUser.setTreePLE(this);
    }
    else
    {
      user.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a treePLE
    if (!this.equals(aUser.getTreePLE()))
    {
      user.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUser()) { index = numberOfUser() - 1; }
      user.remove(aUser);
      user.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(user.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUser()) { index = numberOfUser() - 1; }
      user.remove(aUser);
      user.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfForecasts()
  {
    return 0;
  }

  public Forecast addForecast(String aDescription)
  {
    return new Forecast(aDescription, this);
  }

  public boolean addForecast(Forecast aForecast)
  {
    boolean wasAdded = false;
    if (forecasts.contains(aForecast)) { return false; }
    TreePLE existingTreePLE = aForecast.getTreePLE();
    boolean isNewTreePLE = existingTreePLE != null && !this.equals(existingTreePLE);
    if (isNewTreePLE)
    {
      aForecast.setTreePLE(this);
    }
    else
    {
      forecasts.add(aForecast);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeForecast(Forecast aForecast)
  {
    boolean wasRemoved = false;
    //Unable to remove aForecast, as it must always have a treePLE
    if (!this.equals(aForecast.getTreePLE()))
    {
      forecasts.remove(aForecast);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addForecastAt(Forecast aForecast, int index)
  {  
    boolean wasAdded = false;
    if(addForecast(aForecast))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfForecasts()) { index = numberOfForecasts() - 1; }
      forecasts.remove(aForecast);
      forecasts.add(index, aForecast);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveForecastAt(Forecast aForecast, int index)
  {
    boolean wasAdded = false;
    if(forecasts.contains(aForecast))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfForecasts()) { index = numberOfForecasts() - 1; }
      forecasts.remove(aForecast);
      forecasts.add(index, aForecast);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addForecastAt(aForecast, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfSustainabilityAttributes()
  {
    return 0;
  }

  public SustainabilityAttribute addSustainabilityAttribute(double aIndex, Survey aSurvey)
  {
    return new SustainabilityAttribute(aIndex, this, aSurvey);
  }

  public boolean addSustainabilityAttribute(SustainabilityAttribute aSustainabilityAttribute)
  {
    boolean wasAdded = false;
    if (sustainabilityAttributes.contains(aSustainabilityAttribute)) { return false; }
    TreePLE existingTreePLE = aSustainabilityAttribute.getTreePLE();
    boolean isNewTreePLE = existingTreePLE != null && !this.equals(existingTreePLE);
    if (isNewTreePLE)
    {
      aSustainabilityAttribute.setTreePLE(this);
    }
    else
    {
      sustainabilityAttributes.add(aSustainabilityAttribute);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSustainabilityAttribute(SustainabilityAttribute aSustainabilityAttribute)
  {
    boolean wasRemoved = false;
    //Unable to remove aSustainabilityAttribute, as it must always have a treePLE
    if (!this.equals(aSustainabilityAttribute.getTreePLE()))
    {
      sustainabilityAttributes.remove(aSustainabilityAttribute);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSustainabilityAttributeAt(SustainabilityAttribute aSustainabilityAttribute, int index)
  {  
    boolean wasAdded = false;
    if(addSustainabilityAttribute(aSustainabilityAttribute))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSustainabilityAttributes()) { index = numberOfSustainabilityAttributes() - 1; }
      sustainabilityAttributes.remove(aSustainabilityAttribute);
      sustainabilityAttributes.add(index, aSustainabilityAttribute);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSustainabilityAttributeAt(SustainabilityAttribute aSustainabilityAttribute, int index)
  {
    boolean wasAdded = false;
    if(sustainabilityAttributes.contains(aSustainabilityAttribute))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSustainabilityAttributes()) { index = numberOfSustainabilityAttributes() - 1; }
      sustainabilityAttributes.remove(aSustainabilityAttribute);
      sustainabilityAttributes.add(index, aSustainabilityAttribute);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSustainabilityAttributeAt(aSustainabilityAttribute, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (trees.size() > 0)
    {
      Tree aTree = trees.get(trees.size() - 1);
      aTree.delete();
      trees.remove(aTree);
    }
    
    while (surveys.size() > 0)
    {
      Survey aSurvey = surveys.get(surveys.size() - 1);
      aSurvey.delete();
      surveys.remove(aSurvey);
    }
    
    while (user.size() > 0)
    {
      User aUser = user.get(user.size() - 1);
      aUser.delete();
      user.remove(aUser);
    }
    
    while (forecasts.size() > 0)
    {
      Forecast aForecast = forecasts.get(forecasts.size() - 1);
      aForecast.delete();
      forecasts.remove(aForecast);
    }
    
    while (sustainabilityAttributes.size() > 0)
    {
      SustainabilityAttribute aSustainabilityAttribute = sustainabilityAttributes.get(sustainabilityAttributes.size() - 1);
      aSustainabilityAttribute.delete();
      sustainabilityAttributes.remove(aSustainabilityAttribute);
    }
    
  }

}