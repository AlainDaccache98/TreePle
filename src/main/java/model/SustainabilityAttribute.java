/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.TreePle.model;

// line 69 "../../../../../TreePle.ump"
public class SustainabilityAttribute
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SustainabilityAttribute Attributes
  private double index;

  //SustainabilityAttribute Associations
  private TreePLE treePLE;
  private Survey survey;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SustainabilityAttribute(double aIndex, TreePLE aTreePLE, Survey aSurvey)
  {
    index = aIndex;
    boolean didAddTreePLE = setTreePLE(aTreePLE);
    if (!didAddTreePLE)
    {
      throw new RuntimeException("Unable to create sustainabilityAttribute due to treePLE");
    }
    boolean didAddSurvey = setSurvey(aSurvey);
    if (!didAddSurvey)
    {
      throw new RuntimeException("Unable to create sustainabilityAtribute due to survey");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIndex(double aIndex)
  {
    boolean wasSet = false;
    index = aIndex;
    wasSet = true;
    return wasSet;
  }

  public double getIndex()
  {
    return index;
  }

  public TreePLE getTreePLE()
  {
    return treePLE;
  }

  public Survey getSurvey()
  {
    return survey;
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
      existingTreePLE.removeSustainabilityAttribute(this);
    }
    treePLE.addSustainabilityAttribute(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setSurvey(Survey aSurvey)
  {
    boolean wasSet = false;
    if (aSurvey == null)
    {
      return wasSet;
    }

    Survey existingSurvey = survey;
    survey = aSurvey;
    if (existingSurvey != null && !existingSurvey.equals(aSurvey))
    {
      existingSurvey.removeSustainabilityAtribute(this);
    }
    survey.addSustainabilityAtribute(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    TreePLE placeholderTreePLE = treePLE;
    this.treePLE = null;
    placeholderTreePLE.removeSustainabilityAttribute(this);
    Survey placeholderSurvey = survey;
    this.survey = null;
    placeholderSurvey.removeSustainabilityAtribute(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "index" + ":" + getIndex()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "treePLE = "+(getTreePLE()!=null?Integer.toHexString(System.identityHashCode(getTreePLE())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "survey = "+(getSurvey()!=null?Integer.toHexString(System.identityHashCode(getSurvey())):"null");
  }
}