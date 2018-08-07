package ca.mcgill.ecse321.TreePle.dto;

import java.util.List;

public class ForecastDto {
	
	String description;
	  	  
	  public ForecastDto(String description) {
		  this.description = description;
	  }

	public String getDesciption() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
