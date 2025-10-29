package com.invoice.billing_system.model.product_dto;

import java.util.List;

import com.invoice.billing_system.model.distributor.distributor_dto.ProductRequestDTO;

public class ProductRequestWithStockDTO {
	
    private ProductRequestDTO requestDetails;
    private List<ProductStockView> currentStocks;
    
	public ProductRequestDTO getRequestDetails() {
		return requestDetails;
	}
	public void setRequestDetails(ProductRequestDTO requestDetails) {
		this.requestDetails = requestDetails;
	}
	public List<ProductStockView> getCurrentStocks() {
		return currentStocks;
	}
	public void setCurrentStocks(List<ProductStockView> currentStocks) {
		this.currentStocks = currentStocks;
	}
    
}
