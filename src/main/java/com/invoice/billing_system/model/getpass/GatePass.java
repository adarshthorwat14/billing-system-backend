package com.invoice.billing_system.model.getpass;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GatePass {
    @Id
    private String gatePassId; // Format: GP01/REQID

    private String requestId;
    private String deliveryNoteId;
    private String vehicleNumber;
    private String driverName;
    private String driverPhoneNumber;
    private String fromLocation;
    private String toLocation;
    private String authorisedBy;
    private LocalDate gatePassDate;
    
    private String status;
    
	public String getGatePassId() {
		return gatePassId;
	}
	public void setGatePassId(String gatePassId) {
		this.gatePassId = gatePassId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getDeliveryNoteId() {
		return deliveryNoteId;
	}
	public void setDeliveryNoteId(String deliveryNoteId) {
		this.deliveryNoteId = deliveryNoteId;
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
	public String getDriverPhoneNumber() {
		return driverPhoneNumber;
	}
	public void setDriverPhoneNumber(String driverPhoneNumber) {
		this.driverPhoneNumber = driverPhoneNumber;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public String getAuthorisedBy() {
		return authorisedBy;
	}
	public void setAuthorisedBy(String authorisedBy) {
		this.authorisedBy = authorisedBy;
	}
	public LocalDate getGatePassDate() {
		return gatePassDate;
	}
	public void setGatePassDate(LocalDate gatePassDate) {
		this.gatePassDate = gatePassDate;
	}
    
   
}

