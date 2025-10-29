package com.invoice.billing_system.model.delivery_dto;

import java.util.List;

import com.invoice.billing_system.model.delivery.DeliveryNote;

public class ProductRequestWithDeliveryNoteDTO {
	
	private String requestId;
    private String status;
    private List<DeliveryNote> deliveryNotes;
    
    
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
	public List<DeliveryNote> getDeliveryNotes() {
		return deliveryNotes;
	}
	public void setDeliveryNotes(List<DeliveryNote> deliveryNotes) {
		this.deliveryNotes = deliveryNotes;
	}
    
    
    
    
}
