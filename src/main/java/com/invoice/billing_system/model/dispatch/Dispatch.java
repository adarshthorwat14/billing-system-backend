package com.invoice.billing_system.model.dispatch;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Dispatch {

    @Id
    private String dispatchId;

    private String requestId;
    private String deliveryNoteId;
    private String driverName;
    private String driverPhoneNumber;
    private String vehicleNumber;
    private String fromLocation;
    private String toLocation;
    private String status; // e.g., "DISPATCHED"

    private LocalDate dispatchDate;
    private String authorisedBy;

    // Constructors
    public Dispatch() {}

    public Dispatch(String dispatchId, String requestId, String deliveryNoteId, String driverName,
			String driverPhoneNumber, String vehicleNumber, String fromLocation, String toLocation, String status,
			LocalDate dispatchDate, String authorisedBy) {
		super();
		this.dispatchId = dispatchId;
		this.requestId = requestId;
		this.deliveryNoteId = deliveryNoteId;
		this.driverName = driverName;
		this.driverPhoneNumber = driverPhoneNumber;
		this.vehicleNumber = vehicleNumber;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.status = status;
		this.dispatchDate = dispatchDate;
		this.authorisedBy = authorisedBy;
	}

	public String getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}
	

	public String getAuthorisedBy() {
		return authorisedBy;
	}

	public void setAuthorisedBy(String authorisedBy) {
		this.authorisedBy = authorisedBy;
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

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(LocalDate dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
    
    
    
}
