package ca.mcgill.ecse321.TreePle.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.TreePle.model.Location;
import ca.mcgill.ecse321.TreePle.model.Survey;
import ca.mcgill.ecse321.TreePle.model.Tree;
import ca.mcgill.ecse321.TreePle.model.TreePLE;
import ca.mcgill.ecse321.TreePle.model.Tree.LandTypeEnum;
import ca.mcgill.ecse321.TreePle.model.Tree.SpeciesEnum;

public class TreeDto {
	
	  //Tree Attributes
	  private String municipality;
	  private LandTypeEnum landTypes;
	  private float height;
	  private float diamOfCanopy;
	  private Date datePlanted;
	  private Date dateModified;
	  private SpeciesEnum species;
	  private float id;

	  //Tree Associations
	  private List<SurveyDto> surveies;
	  //private TreePLE treePLE;
	  private LocationDto location;
	  
	  public TreeDto() {}
	  
	  
	  public TreeDto(String aMunicipality, LandTypeEnum aLandTypes, float aHeight, float aDiamOfCanopy, Date aDatePlanted, Date aDateModified, SpeciesEnum aSpecies, LocationDto aLocation, float aId, List<SurveyDto> aSurvey)
	  {
	    municipality = aMunicipality;
	    landTypes = aLandTypes;
	    height = aHeight;
	    diamOfCanopy = aDiamOfCanopy;
	    datePlanted = aDatePlanted;
	    dateModified = aDateModified;
	    species = aSpecies;
	    surveies = new ArrayList<SurveyDto>();
	    this.setSurveies(aSurvey);
	    id = aId;
	    /*boolean didAddLocation = setLocation(aLocation);
	    if (!didAddLocation)
	    {
	      throw new RuntimeException("Unable to create tree due to location");
	    }*/
	    setLocation(aLocation);
	  }

	  /*
	public boolean setLocation(LocationDto aNewLocation) {
		boolean wasSet = false;
	    if (aNewLocation == null)
	    {
	      //Unable to setLocation to null, as tree must always be associated to a location
	      return wasSet;
	    }
	    
	    TreeDto existingTree = aNewLocation.getTree();
	    if (existingTree != null && !equals(existingTree))
	    {
	      //Unable to setLocation, the current location already has a tree, which would be orphaned if it were re-assigned
	      return wasSet;
	    }

	    this.location = aNewLocation;
	    wasSet = true;
	    return wasSet;
	}*/
	  
	  public float getId() {
		return id;
	}


	public void setId(float id) {
		this.id = id;
	}


	public String getMunicipality() {
		return municipality;
	}


	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}


	public LandTypeEnum getLandTypes() {
		return landTypes;
	}


	public void setLandTypes(LandTypeEnum landTypes) {
		this.landTypes = landTypes;
	}


	public float getHeight() {
		return height;
	}


	public void setHeight(float height) {
		this.height = height;
	}


	public float getDiamOfCanopy() {
		return diamOfCanopy;
	}


	public void setDiamOfCanopy(float diamOfCanopy) {
		this.diamOfCanopy = diamOfCanopy;
	}


	public Date getDatePlanted() {
		return datePlanted;
	}


	public void setDatePlanted(Date datePlanted) {
		this.datePlanted = datePlanted;
	}


	public Date getDateModified() {
		return dateModified;
	}


	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}


	public SpeciesEnum getSpecies() {
		return species;
	}


	public void setSpecies(SpeciesEnum species) {
		this.species = species;
	}


	public List<SurveyDto> getSurveies() {
		return surveies;
	}


	public void setSurveies(SurveyDto surveies) {
		this.surveies.add(surveies);
	}
	
	public void setSurveies(List<SurveyDto> surveies) {
		this.surveies=surveies;
	}


	public void setLocation(LocationDto newLocation) {
		  this.location = newLocation;
	  }


	
	public LocationDto getLocation() {
		return location;
	}
}