package ca.mcgill.ecse321.TreePle.dto;


public class SustainabilityAttributeDto {
	private Double index;	  
	public SustainabilityAttributeDto(){}
	  
	public SustainabilityAttributeDto(Double index){
		this.index = index;
	  }

	public Double getIndex() {
		return index;
	}

	public void setIndex(Double index) {
		this.index = index;
	}
}
