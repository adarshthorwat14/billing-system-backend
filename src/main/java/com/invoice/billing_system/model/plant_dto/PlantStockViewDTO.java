package com.invoice.billing_system.model.plant_dto;

import java.time.LocalDate;

public class PlantStockViewDTO {
	
    private String productId;
    private String productName;
    private Integer stockQuantity;
    private LocalDate mfgDate; // stock added date (from Product.mfgDate)
    
    
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
	public Integer getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public LocalDate getMfgDate() {
		return mfgDate;
	}
	public void setMfgDate(LocalDate mfgDate) {
		this.mfgDate = mfgDate;
	}
	
	public PlantStockViewDTO(String productId, String productName, Integer stockQuantity, LocalDate mfgDate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.stockQuantity = stockQuantity;
		this.mfgDate = mfgDate;
	}
	
	public PlantStockViewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
