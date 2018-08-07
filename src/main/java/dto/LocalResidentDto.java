package ca.mcgill.ecse321.TreePle.dto;

import java.util.ArrayList;
import java.util.List;


public class LocalResidentDto {
	//tbd
	private List<PropertyDto> propertiesdto;
	private List<SurveyDto> surveysdto;
	
	private String name;
	private String email;
	private String id;
	  
	public LocalResidentDto() {
		//tbd
	  }
	
	public LocalResidentDto(String aName, String aEmail, String aId, PropertyDto aProperty) {
		name = aName;
		email = aEmail;
		id =aId;
		propertiesdto = new ArrayList<PropertyDto>();
		setPropertiesdto(aProperty);
		surveysdto = new ArrayList<SurveyDto>();
	}

	public List<PropertyDto> getPropertiesdto() {
		return propertiesdto;
	}

	public void setPropertiesdto(PropertyDto propertiesdto) {
		this.propertiesdto.add(propertiesdto);
	}

	public List<SurveyDto> getSurveysdto() {
		return surveysdto;
	}

	public void setSurveysdto(List<SurveyDto> surveysdto) {
		this.surveysdto = surveysdto;
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