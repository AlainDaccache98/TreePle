package ca.mcgill.ecse321.TreePle.dto;

import ca.mcgill.ecse321.TreePle.model.Location;

public class LocationDto {

	private float latitude;
	private float longitude;
	
	private TreeDto tree;
	private PropertyDto property;
	
	public LocationDto() {}
	
	public LocationDto(float aLatitude, float aLongitude) {
		latitude = aLatitude;
		longitude = aLongitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public TreeDto getTree() {
		return tree;
	}

	/*
	public boolean setTree(TreeDto aNewTree) {
		boolean wasSet = false;
	    if (tree != null && !tree.equals(aNewTree) && equals(tree.getLocation()))
	    {
	      //Unable to setTree, as existing tree would become an orphan
	      return wasSet;
	    }

	    tree = aNewTree;
	    LocationDto anOldLocation = aNewTree != null ? aNewTree.getLocation() : null;

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
	}*/
	
	public void setTree(TreeDto newTree) {
		this.tree = newTree;
	}

	public PropertyDto getProperty() {
		return property;
	}

	/*
	public boolean setProperty(PropertyDto aNewProperty) {

	    boolean wasSet = false;
	    if (property != null && !property.equals(aNewProperty) && equals(property.getLocation()))
	    {
	      //Unable to setProperty, as existing property would become an orphan
	      return wasSet;
	    }

	    property = aNewProperty;
	    LocationDto anOldLocation = aNewProperty != null ? aNewProperty.getLocation() : null;

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
	}*/
	
	public void setProperty(PropertyDto newProperty) {
		this.property = newProperty;
	}
}
