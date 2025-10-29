package com.invoice.billing_system.model.logistic;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity

@Table(name = "logistic_vehicles")
public class LogisticVehicle {

    @Id
    private String vehicleId;         // e.g. VHL1001
    private String vehicleNumber;     // e.g. MH12AB1234
    private String vehicleType;
    private String driverName;
    private String driverPhone;
    private String capacity;          // e.g. 10 tons
    private String status;            // ACTIVE, INACTIVE, ON_ROUTE
    private String assignedPlant;     // e.g. PUNE, KOLHAPUR
    private LocalDate lastMaintenanceDate;
    private LocalDate registerDate;
    
    
    
    
	public LogisticVehicle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getDriverPhone() {
		return driverPhone;
	}
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAssignedPlant() {
		return assignedPlant;
	}
	public void setAssignedPlant(String assignedPlant) {
		this.assignedPlant = assignedPlant;
	}
	public LocalDate getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}
	public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}
	public LocalDate getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

    
}
