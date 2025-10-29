package com.invoice.billing_system.model.delivery_dto;

public class DeliveryNoteLogisticsDTO {
	
	    private String deliveryNoteId;
	    private String requestId;
	    private String distributorId;
	    private String distributorName;
	    private String productId;
	    private String productName;
	    private int quantity;
	    private String unit;
	    private String fromLocation;
	    private String toLocation;
	    private String status;
	    
	    private String region;
	    private String branch;
	    
		public String getDeliveryNoteId() {
			return deliveryNoteId;
		}
		public void setDeliveryNoteId(String deliveryNoteId) {
			this.deliveryNoteId = deliveryNoteId;
		}
		public String getRequestId() {
			return requestId;
		}
		public void setRequestId(String requestId) {
			this.requestId = requestId;
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
	    
}
