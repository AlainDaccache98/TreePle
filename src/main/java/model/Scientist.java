/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePle.model;
import java.util.*;

import ca.mcgill.ecse321.TreePle.model.Survey.StatusEnum;

import java.sql.Date;

// line 39 "../../../../../TreePle.ump"
public class Scientist extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Scientist Associations
  private List<Survey> surveies;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Scientist(String aName, String aEmail, String aUserID, TreePLE aTreePLE)
  {
    super(aName, aEmail, aUserID, aTreePLE);
    surveies = new ArrayList<Survey>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public static int minimumNumberOfSurveies()
  {
    return 0;
  }

  public Survey addSurvey(Date aDate, StatusEnum aStatus, Tree aTrees, TreePLE aTreePLE)
  {
    return new Survey(aDate, aStatus, aTrees, this, aTreePLE);
  }

  public boolean addSurvey(Survey aSurvey)
  {
    boolean wasAdded = false;
    if (surveies.contains(aSurvey)) { return false; }
    Scientist existingUser = aSurvey.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aSurvey.setUser(this);
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
    //Unable to remove aSurvey, as it must always have a user
    if (!this.equals(aSurvey.getUser()))
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

  public void delete()
  {
    for(int i=surveies.size(); i > 0; i--)
    {
      Survey aSurvey = surveies.get(i - 1);
      aSurvey.delete();
    }
    super.delete();
  }

}