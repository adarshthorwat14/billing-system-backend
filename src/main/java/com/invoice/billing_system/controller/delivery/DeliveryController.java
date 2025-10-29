package com.invoice.billing_system.controller.delivery;

import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.billing_system.model.delivery.DeliveryNote;
import com.invoice.billing_system.model.delivery_dto.DeliveryNoteDto;
import com.invoice.billing_system.model.delivery_dto.DeliveryNoteLogisticsDTO;
import com.invoice.billing_system.repository.delivery.DeliveryNoteRepository;
import com.invoice.billing_system.service.delivery.DeliveryNoteService;
import com.invoice.billing_system.service.pdf_serv.PdfService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
	
	@Autowired
	private DeliveryNoteRepository deliveryNoteRepository;
	
	@Autowired
	private DeliveryNoteService deliveryNoteService;
	
	
//	@GetMapping("/delivery-notes/by-request/{requestId}")
//	public List<DeliveryNote> getNotesByRequest(@PathVariable String requestId) {
//	    return deliveryNoteRepository.findByRequestId(requestId);
//	}
	
	@GetMapping("/delivery-notes/all")
    public List<DeliveryNote> getAllDeliveryNotes() {
        return deliveryNoteRepository.findAll();
    }
	
	@GetMapping("/delivery-notes/by-request/{requestId}")
    public ResponseEntity<List<DeliveryNote>> getDeliveryNotesByRequestId(@PathVariable String requestId) {
        List<DeliveryNote> notes = deliveryNoteRepository.findByRequestId(requestId);
        if (notes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notes);
    }
	
	
	
	
	@GetMapping("/{distributorId}/delivery-notes")
    public ResponseEntity<List<DeliveryNoteDto>> getDeliveryNotesByDistributor(
            @PathVariable String distributorId) {
        List<DeliveryNoteDto> notes = deliveryNoteService.getDeliveryNotesByDistributor(distributorId);
        return ResponseEntity.ok(notes);
    }
	
	 // 3️⃣ Download delivery note as PDF
//	@GetMapping("/distributors/{distributorId}/delivery-notes/{noteId}/download")
//	public void downloadDeliveryNote(
//	        @PathVariable String distributorId,
//	        @PathVariable String noteId,
//	        HttpServletResponse response) {
//
//	    try {
//	        // 1️⃣ Fetch the delivery note and generate PDF
//	        DeliveryNoteDto note = deliveryNoteService.getDeliveryNoteById(distributorId, noteId);
////	        byte[] pdfBytes = PdfService.generateDeliveryNotePdf(note); // your PDF generator
//
//	        // 2️⃣ Set response headers
//	        response.setContentType("application/pdf");
//	        response.setHeader("Content-Disposition", "attachment; filename=deliveryNote_" + noteId + ".pdf");
//	        response.setContentLength(pdfBytes.length);
//
//	        // 3️⃣ Write PDF bytes to output stream
//	        OutputStream os = response.getOutputStream();
//	        os.write(pdfBytes);
//	        os.flush();
//	        os.close();
//
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
    
    @GetMapping("/{distributorId}/delivery-notes/{deliveryNoteId}")
    public ResponseEntity<DeliveryNoteDto> getDeliveryNote(
            @PathVariable String distributorId,
            @PathVariable String deliveryNoteId) {
    	DeliveryNoteDto note = deliveryNoteService.getDeliveryNoteById(distributorId, deliveryNoteId);
        return ResponseEntity.ok(note);
    }
}
