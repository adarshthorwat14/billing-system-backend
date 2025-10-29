package com.invoice.billing_system.model.plant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PlantEmployee {

    @Id
    private String employeeId;

    private String employeeName;
    private String email;
    private String position;
    private String password;
    private String plantId;
    private String plantName;
    
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
    
	
    
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
    
	public PlantEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlantEmployee(String employeeId, String employeeName, String email, String password, String position, String plantId,
			String plantName) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.email = email;
		this.position = position;
		this.plantId = plantId;
		this.plantName = plantName;
		this.password = password;
	}
    
    
}

