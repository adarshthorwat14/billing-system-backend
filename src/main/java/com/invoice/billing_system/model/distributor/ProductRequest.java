package com.invoice.billing_system.model.distributor;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ProductRequest {

    @Id
    private String requestId;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;

    private LocalDate requestDate;
    private LocalDate billingDate;

    private String status; // PENDING, ACCEPTED, RELEASED, DISPATCHED, DELIVERED
    private String priority;
    private String notes;
    private String requestedBy;
    

    private int totalQuantity;
    private int totalItems;
    // 👉 Newly Added Properties (order-related):
    private String plantId;
    private String plantName;

    private String deliveryFrom;
    private String deliveryTo;
    private String region;
    private String branch;
    private String salesPerson;
    private double cgst;
    private double sgst;
    private double igst;

    private double subTotal;
    private double productValue;
    private double finalValue;

    @Column(length = 500)
    private String remark;

    @OneToMany(mappedBy = "productRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductRequestItem> requestItems;

    // Constructor
    public ProductRequest() {}

    public ProductRequest(String requestId, Distributor distributor, String status,LocalDate requestDate,
                          String priority, String notes, String requestedBy, int totalQuantity,int totalItems,
                          List<ProductRequestItem> requestItems, String plantId,String salesPerson, String plantName,
                          String deliveryFrom, String deliveryTo, double cgst, double sgst, double igst,
                          double subTotal, double productValue, String region, String branch, double finalValue, String remark) {
        this.requestId = requestId;
        this.distributor = distributor;
        this.status = status;
        this.requestDate = requestDate;
        this.priority = priority;
        this.notes = notes;
        this.requestedBy = requestedBy;
        this.totalQuantity = totalQuantity;
        this.totalItems = totalItems;
        this.requestItems = requestItems;
        this.billingDate = LocalDate.now();
        this.plantId = plantId;
        this.plantName = plantName;
        this.deliveryFrom = deliveryFrom;
        this.deliveryTo = deliveryTo;
        this.cgst = cgst;
        this.sgst = sgst;
        this.igst = igst;
        this.subTotal = subTotal;
        this.productValue = productValue;
        this.finalValue = finalValue;
        this.remark = remark;
        this.salesPerson = salesPerson;
        this.region = region;
        this.branch = branch;
        }

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	
	public String getRegion() {
		return region;
	}

	
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	public LocalDate getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(LocalDate billingDate) {
		this.billingDate = billingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public LocalDate getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
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

	public String getDeliveryFrom() {
		return deliveryFrom;
	}

	public void setDeliveryFrom(String deliveryFrom) {
		this.deliveryFrom = deliveryFrom;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ProductRequestItem> getRequestItems() {
		return requestItems;
	}

	public void setRequestItems(List<ProductRequestItem> requestItems) {
		this.requestItems = requestItems;
	}

   
    
    
}
