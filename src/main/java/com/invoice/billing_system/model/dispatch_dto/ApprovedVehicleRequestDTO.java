package com.invoice.billing_system.model.dispatch_dto;

import java.util.List;

import com.invoice.billing_system.model.distributor.distributor_dto.ProductRequestItemDTO;

public class ApprovedVehicleRequestDTO {
   
	 private String requestId;
	    private String deliveryNoteId;

	    private List<ProductRequestItemDTO> requestItems;

	    private String fromLocation;
	    private String toLocation;

	    private String vehicleNumber;
	    private String driverName;
	    
	    
		public String getRequestId() {
			return requestId;
		}
		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}
		public String getDeliveryNoteId() {
			return deliveryNoteId;
		}
		public void setDeliveryNoteId(String deliveryNoteId) {
			this.deliveryNoteId = deliveryNoteId;
		}
		public List<ProductRequestItemDTO> getRequestItems() {
			return requestItems;
		}
		public void setRequestItems(List<ProductRequestItemDTO> requestItems) {
			this.requestItems = requestItems;
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
		public String getVehicleNumber() {
			return vehicleNumber;
		}
		public void setVehicleNumber(String vehicleNumber) {
			this.vehicleNumber = vehicleNumber;
		}
		public String getDriverName() {
			return driverName;
		}
		public void setDriverName(String driverName) {
			this.driverName = driverName;
		}
    
    
}

