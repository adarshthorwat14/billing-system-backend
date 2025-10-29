package com.invoice.billing_system.model.plant_dto;

public class PlantStockDto {

		private String plantId;
	    private String plantName;
	    private String productId;
	    private String productName;
	    private int availableStock;
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
		public int getAvailableStock() {
			return availableStock;
		}
		public void setAvailableStock(int availableStock) {
			this.availableStock = availableStock;
		}
	    
	    
}
