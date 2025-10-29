package com.invoice.billing_system.model.distributor.distributor_dto;

public class DistributorStockDTO {
    private String productId;
    private String productName;
    private String unit;
    private String plantId;     // 🆕 Plant reference
    private String plantName; 
    private int totalAllocated;
    private int approvedQuantity;
    
    
    
	public int getApprovedQuantity() {
		return approvedQuantity;
	}
	public void setApprovedQuantity(int approvedQuantity) {
		this.approvedQuantity = approvedQuantity;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getTotalAllocated() {
		return totalAllocated;
	}
	public void setTotalAllocated(int totalAllocated) {
		this.totalAllocated = totalAllocated;
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

	
	public DistributorStockDTO(String productId, String productName, String unit, String plantId, String plantName,
			int totalAllocated, int approvedQuantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.unit = unit;
		this.plantId = plantId;
		this.plantName = plantName;
		this.totalAllocated = totalAllocated;
		this.approvedQuantity = approvedQuantity;
	}
	
	public DistributorStockDTO() {
		// TODO Auto-generated constructor stub
	}
	
    
}
