package com.invoice.billing_system.model.plant_dto;

public class PlantEmployeeLoginResponseDTO {
	
	private String id;
    private String name;
    private String email;
    private String position;
    private String plantId;
    private String plantName;
	public String getId() {
		return id;
	}
	
	
	
	public PlantEmployeeLoginResponseDTO() {
		super();
	}



	public PlantEmployeeLoginResponseDTO(String id, String name, String email, String position, String plantId,
			String plantName) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.position = position;
		this.plantId = plantId;
		this.plantName = plantName;
	}



	public void setId(String id) {
		this.id = id;
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public String getPlantName() {
		return plantName;
	}
	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
    
    
}
