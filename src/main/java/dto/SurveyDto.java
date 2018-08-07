package ca.mcgill.ecse321.TreePle.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.TreePle.model.Survey.StatusEnum;

public class SurveyDto {
	
	//Survey Attributes
	private Date date;
	private StatusEnum status;

	//Survey Associations
	private float trees;
	private List<SustainabilityAttributeDto> sustainabilityAtributes;
	private ScientistDto scientist;
	
	//NEED to change the surveyDto when sustainability is down
	/*public SurveyDto(Date aDate, StatusEnum aStatus, TreeDto aTrees, ScientistDto aScientist, List<SustainabilityAttributeDto> aSustainability, List<ForecastDto> aForecast) {
		this.date = aDate;
		this.scientist = aScientist;
		this.status = aStatus;
		this.trees = aTrees;
		sustainabilityAtributes = new ArrayList<SustainabilityAttributeDto>();
		forecasts = new ArrayList<ForecastDto>();
		this.setForecasts(aForecast);
		this.setSustainabilityAtributes(aSustainability);
		aTrees.setSurveies(this);
		aScientist.setSurveies(this);
	  }*/
	
	public SurveyDto() {}
	
	public SurveyDto(Date aDate, StatusEnum aStatus, float aTreeId, ScientistDto aScientist) {
		this.date = aDate;
		this.scientist = aScientist;
		this.status = aStatus;
		this.trees = aTreeId;
		sustainabilityAtributes = new ArrayList<SustainabilityAttributeDto>();
	}
	  
	public Date getDate() {
		return date;
	}
	  
	public void setDate(Date date) {
		this.date = date;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public float getTrees() {
		return trees;
	}

	public void setTrees(float treesId) {
		this.trees = treesId;
	}

	public List<SustainabilityAttributeDto> getSustainabilityAtributes() {
		return sustainabilityAtributes;
	}

	public void setSustainabilityAtributes(SustainabilityAttributeDto sustainabilityAtributes) {
		this.sustainabilityAtributes.add(sustainabilityAtributes);
	}
	
	public void setSustainabilityAtributes(List<SustainabilityAttributeDto> sustainabilityAtributes) {
		this.sustainabilityAtributes = sustainabilityAtributes;
	}

	public ScientistDto getScientist() {
		return scientist;
	}

	public void setScientist(ScientistDto scientist) {
		this.scientist = scientist;
	}

}