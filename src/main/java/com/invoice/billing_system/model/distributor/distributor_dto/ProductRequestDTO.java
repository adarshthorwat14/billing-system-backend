package com.invoice.billing_system.model.distributor.distributor_dto;


import java.time.LocalDate;
import java.util.List;


public class ProductRequestDTO {

    private String distributorId;
    private String distributorName;
    private String requestId;
    private LocalDate requestDate;
    private String status;         // e.g., PENDING
    private String priority;       // e.g., HIGH, MEDIUM
    private String notes;
    private String requestedBy;
    private String remark;
    private String region;
    private String branch;
    private String salesPerson;
    private int totalQuantity;
    private int totalItems;
    
    private LocalDate billingDate;
    private String deliveryTo;

    private double cgst;
    private double sgst;
    private double igst;

    private double subTotal;
    private double productValue;
    private double finalValue;

    private List<ProductRequestItemDTO> requestItems;
    
	public LocalDate getBillingDate() {
		return billingDate;
	}

	
	
	public String getDistributorName() {
		return distributorName;
	}



	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}



	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = billingDate;
	}

	public String getDeliveryTo() {
		return deliveryTo;
	}

	public void setDeliveryTo(String deliveryTo) {
		this.deliveryTo = deliveryTo;
	}

	public double getCgst() {
		return cgst;
	}

	public void setCgst(double cgst) {
		this.cgst = cgst;
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



	public String getSalesPerson() {
		return salesPerson;
	}



	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}



	public double getSgst() {
		return sgst;
	}

	public void setSgst(double sgst) {
		this.sgst = sgst;
	}

	public double getIgst() {
		return igst;
	}

	public void setIgst(double igst) {
		this.igst = igst;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getProductValue() {
		return productValue;
	}

	public void setProductValue(double productValue) {
		this.productValue = productValue;
	}

	public double getFinalValue() {
		return finalValue;
	}

	public void setFinalValue(double finalValue) {
		this.finalValue = finalValue;
	}

	public String getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
	}

	public LocalDate getRequestDate() {
		return requestDate;
	}
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestId() {
	    return requestId;
	}

	public void setRequestId(String requestId) {
	    this.requestId = requestId;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public int getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}

	public List<ProductRequestItemDTO> getRequestItems() {
		return requestItems;
	}

	public void setRequestItems(List<ProductRequestItemDTO> requestItems) {
		this.requestItems = requestItems;
	}

	public ProductRequestDTO(String distributorId, LocalDate requestDate, String status, String priority, String notes,
			String requestedBy, int totalQuantity, int totalItems, List<ProductRequestItemDTO> requestItems) {
		super();
		this.distributorId = distributorId;
		this.requestDate = requestDate;
		this.status = status;
		this.priority = priority;
		this.notes = notes;
		this.requestedBy = requestedBy;
		this.totalQuantity = totalQuantity;
		this.totalItems = totalItems;
		this.requestItems = requestItems;
	}
    
    
    public ProductRequestDTO() {
		// TODO Auto-generated constructor stub
	}
}


