package com.invoice.billing_system.model.distributor.distributor_dto;

public class ProductRequestItemDTO {

    private String productId;
    private String productName;        // optional (for display/logging)
    private int quantityRequested;
    private String unit;
    private String status;             // e.g., PENDING
    private String description;
    
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
	public int getQuantityRequested() {
		return quantityRequested;
	}
	public void setQuantityRequested(int quantityRequested) {
		this.quantityRequested = quantityRequested;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ProductRequestItemDTO(String productId, String productName, int quantityRequested, String unit,
			String status, String description) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantityRequested = quantityRequested;
		this.unit = unit;
		this.status = status;
		this.description = description;
	}
    
    
    public ProductRequestItemDTO() {
		// TODO Auto-generated constructor stub
	}
}
