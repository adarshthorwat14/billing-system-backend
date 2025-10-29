package com.invoice.billing_system.model.plant_dto;

public class PlantDTO {
    private String plantId;
    private String name;
    private String location;
    private String description;
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public PlantDTO(String plantId, String name, String location, String description) {
		super();
		this.plantId = plantId;
		this.name = name;
		this.location = location;
		this.description = description;
	}
	public PlantDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
}
