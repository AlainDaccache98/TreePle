package ca.mcgill.ecse321.TreePle.dto;

import java.util.ArrayList;
import java.util.List;

public class ScientistDto {
	  
	private List<SurveyDto> surveies;
	  
	private String name;
	private String email;
	private String id;	
	
	public ScientistDto() {
		
	  }
	
	public ScientistDto(String name, String email, String id) {
		this.name = name;
		this.email = email;
		this.id = id;
		this.surveies = new ArrayList<SurveyDto> ();
	}

	public List<SurveyDto> getSurveies() {
		return surveies;
	}

	public void setSurveies(SurveyDto surveies) {
		this.surveies.add(surveies);
	}
	
	public void setSurveies(List<SurveyDto> surveies) {
		this.surveies = surveies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}