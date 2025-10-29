package com.invoice.billing_system.service.delivery;

import java.util.List;

import com.invoice.billing_system.model.delivery.DeliveryNote;
import com.invoice.billing_system.model.delivery_dto.DeliveryNoteDto;
import com.invoice.billing_system.model.delivery_dto.DeliveryNoteLogisticsDTO;

public interface DeliveryNoteService {
	
	 List<DeliveryNote> getAllDeliveryNotes();
	 List<DeliveryNote> getDeliveryNotesForLogistics();
	 List<DeliveryNote> getAllDeliveryNotesWithoutVehicleRequest();
	 List<DeliveryNoteDto> getDeliveryNotesByDistributor(String distributorId);
	 DeliveryNoteDto getDeliveryNoteById(String distributorId, String deliveryNoteId);
	 byte[] generateDeliveryNotePDF(String distributorId, String deliveryNoteId);


}
