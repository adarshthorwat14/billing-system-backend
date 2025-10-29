package com.invoice.billing_system.model.delivery;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DeliveryNote {

    @Id
    private String deliveryNoteId;

    private LocalDate date;
    private String requestId;
    private String productId;
    private String productName;
    private int quantity;
    private String unit;
    private String distributorId;
    private String distributorName;
    private String status; // e.g., DELIVERY_CREATED
    private String fromLocation;
    private String toLocation;
    private String region;
    private String branch;
    
    // Optional reference to Plant (if needed)
    private String plantId;
    private String plantName;

	public String getDeliveryNoteId() {
		return deliveryNoteId;
	}

	public void setDeliveryNoteId(String deliveryNoteId) {
		this.deliveryNoteId = deliveryNoteId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	
	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	
	public String getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlantId() {
		return plantId;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}

	public DeliveryNote(String deliveryNoteId, LocalDate date, String requestId, String productId, String productName,
			int quantity, String unit, String distributorName, String status, String plantId) {
		super();
		this.deliveryNoteId = deliveryNoteId;
		this.date = date;
		this.requestId = requestId;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.unit = unit;
		this.distributorName = distributorName;
		this.status = status;
		this.plantId = plantId;
	}

	public DeliveryNote() {
		// TODO Auto-generated constructor stub
	}

    // Getters and Setters...
    
 
}

