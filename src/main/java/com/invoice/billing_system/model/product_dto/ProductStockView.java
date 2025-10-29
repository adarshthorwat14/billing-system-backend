package com.invoice.billing_system.model.product_dto;

public class ProductStockView {
    private String productId;
    private String productName;
    private String plantId;
    private int availableStock;
    
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	
	
	public String getPlantId() {
		return plantId;
	}
	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getAvailableStock() {
		return availableStock;
	}
	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}
      
}
