package ca.mcgill.ecse321.TreePle.dto;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.TreePle.model.LocalResident;
import ca.mcgill.ecse321.TreePle.model.Location;
import ca.mcgill.ecse321.TreePle.model.Property;

public class PropertyDto {

	  //Property Attributes
	  private float length;
	  private float width;

	  //Property Associations
	  private List<LocalResidentDto> localResidents;
	  private LocationDto location;
	  
	  public PropertyDto() {}
	  
	  public PropertyDto(float aLength, float aWidth, LocationDto aLocation)
	  {
	    length = aLength;
	    width = aWidth;
	    localResidents = new ArrayList<LocalResidentDto>();
	    //need to consider adding local resident to the dto??
	    /*boolean didAddLocation = setLocation(aLocation);
	    if (!didAddLocation)
	    {
	      throw new RuntimeException("Unable to create property due to location");
	    }*/
	    location = aLocation;
	  }

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public List<LocalResidentDto> getLocalResidents() {
		return localResidents;
	}

	public void setLocalResidents(LocalResidentDto localResidents) {
		this.localResidents.add(localResidents);
	}

	public LocationDto getLocation() {
		return location;
	}
	/*
	public boolean setLocation(LocationDto aNewLocation) {
	    boolean wasSet = false;
	    if (aNewLocation == null)
	    {
	      //Unable to setLocation to null, as property must always be associated to a location
	      return wasSet;
	    }
	    
	    PropertyDto existingProperty = aNewLocation.getProperty();
	    if (existingProperty != null && !equals(existingProperty))
	    {
	      //Unable to setLocation, the current location already has a property, which would be orphaned if it were re-assigned
	      return wasSet;
	    }
	    
	    LocationDto anOldLocation = location;
	    location = aNewLocation;
	    location.setProperty(this);

	    if (anOldLocation != null)
	    {
	      anOldLocation.setProperty(null);
	    }
	    wasSet = true;
	    return wasSet;
		
	}*/
	
	public void setLocation(LocationDto newLocation) {
		this.location = newLocation;
	}
	  
}
