package com.invoice.billing_system.service.delivery;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.billing_system.model.delivery.DeliveryNote;
import com.invoice.billing_system.model.delivery_dto.DeliveryNoteDto;
import com.invoice.billing_system.model.delivery_dto.DeliveryNoteLogisticsDTO;
import com.invoice.billing_system.model.vehicle.VehicleRequestPlant;
import com.invoice.billing_system.repository.delivery.DeliveryNoteRepository;
import com.invoice.billing_system.repository.vehicle.VehicleRepository;

@Service
public class DeliveryNoteServiceImpl implements DeliveryNoteService {

    private final DeliveryNoteRepository deliveryNoteRepository;
    
    @Autowired
    private VehicleRepository vehicleRepository;

    public DeliveryNoteServiceImpl(DeliveryNoteRepository deliveryNoteRepository) {
        this.deliveryNoteRepository = deliveryNoteRepository;
    }

    @Override
    public List<DeliveryNote> getAllDeliveryNotes() {
        return deliveryNoteRepository.findAll();
    }

    public List<DeliveryNote> getAllDeliveryNotesWithoutVehicleRequest() {
        List<DeliveryNote> allNotes = deliveryNoteRepository.findAll();
        List<String> forwardedRequestIds = vehicleRepository.findByStatus("FORWARDED")
                .stream()
                .map(VehicleRequestPlant::getRequestId)
                .toList();

        return allNotes.stream()
                .filter(note -> !forwardedRequestIds.contains(note.getRequestId()))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<DeliveryNote> getDeliveryNotesForLogistics() {
        // Fetch only notes with status 'DELIVERY_CREATED'
        return deliveryNoteRepository.findByStatus("DELIVERY_CREATED");
    }
    
    
    @Override
    public List<DeliveryNoteDto> getDeliveryNotesByDistributor(String distributorId) {
        List<DeliveryNote> notes = deliveryNoteRepository.findByDistributorId(distributorId);
        return notes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public DeliveryNoteDto getDeliveryNoteById(String distributorId, String deliveryNoteId) {
        DeliveryNote note = deliveryNoteRepository.findByDistributorIdAndDeliveryNoteId(distributorId, deliveryNoteId)
                .orElseThrow(() -> new RuntimeException("Delivery note not found"));
        return convertToDTO(note);
    }

    @Override
    public byte[] generateDeliveryNotePDF(String distributorId, String deliveryNoteId) {
    	DeliveryNoteDto note = getDeliveryNoteById(distributorId, deliveryNoteId);

        // You can use iText, Apache PDFBox, or OpenPDF to generate PDF
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // ... PDF generation logic here, add distributor name, order message, product table ...
        return out.toByteArray();
    }

    private DeliveryNoteDto convertToDTO(DeliveryNote note) {
    	DeliveryNoteDto dto = new DeliveryNoteDto();
        dto.setDeliveryNoteId(note.getDeliveryNoteId());
        dto.setRequestId(note.getRequestId());
        dto.setDistributorId(note.getDistributorId());
        dto.setDistributorName(note.getDistributorName());
        dto.setProductId(note.getProductId());
        dto.setProductName(note.getProductName());
        dto.setQuantity(note.getQuantity());
        dto.setUnit(note.getUnit());
        dto.setFromLocation(note.getPlantName());
        dto.setDate(note.getDate());
        dto.setStatus(note.getStatus());
        return dto;
    }

}
