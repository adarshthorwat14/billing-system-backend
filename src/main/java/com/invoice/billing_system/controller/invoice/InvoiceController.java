package com.invoice.billing_system.controller.invoice;


import com.invoice.billing_system.model.invoice.Invoice;
import com.invoice.billing_system.model.invoice_dto.DistributorClientInvoiceDTO;
import com.invoice.billing_system.model.invoice_dto.InvoiceRequest;
import com.invoice.billing_system.model.invoice_dto.PaymentStatusDTO;
import com.invoice.billing_system.repository.invoice_repo.InvoiceRepository;
import com.invoice.billing_system.repository.product_repo.ProductRepository;
import com.invoice.billing_system.service.invoice_serv.InvoiceService;
import com.invoice.billing_system.service.pdf_serv.PdfService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceController(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
    
    @Autowired
    private PdfService pdfService;
    
//    @PostMapping
//    public Invoice createInvoice(@RequestBody InvoiceRequest request) {
//        return invoiceService.createInvoice(request.getClientId(), request.getItems());
//    }
    
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getInvoiceCount() {
        long count = invoiceRepository.count();
        return ResponseEntity.ok(Collections.singletonMap("count", count));
    }
    
    @PostMapping
    public ResponseEntity<?> createInvoice(@RequestBody InvoiceRequest request) {
        try {
            Invoice invoice = invoiceService.createInvoice(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of("message", "Invoice created successfully", "invoiceId", invoice.getInvoiceId())
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("error", e.getMessage())
            );
        }
    }
    
    @GetMapping("/distributor/{distributorId}")
    public ResponseEntity<List<DistributorClientInvoiceDTO>> getInvoicesByDistributor(@PathVariable String distributorId) {
        return ResponseEntity.ok(invoiceService.getInvoicesByDistributor(distributorId));
    }
    
    
    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }
    
    @GetMapping("/{invoiceId}")
    public Invoice getInvoiceById(@PathVariable String invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }
    
    @GetMapping("/client/{clientId}")
    public List<Invoice> getInvoicesByClient(@PathVariable String clientId) {
        return invoiceRepository.findByClientId(clientId);
    }

    
    @GetMapping("/{invoiceId}/pdf")
    public ResponseEntity<ByteArrayResource> downloadInvoicePdf(@PathVariable String invoiceId) {
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        byte[] pdfData = pdfService.generateInvoicePdf(invoice);
        ByteArrayResource resource = new ByteArrayResource(pdfData);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice_" + invoiceId + ".pdf")
                .contentLength(pdfData.length)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
    
    @GetMapping("/payment-status")
    public List<PaymentStatusDTO> getAllClientPaymentStatuses() {
        return invoiceService.getAllClientPaymentStatuses();
    }
    
    @PutMapping("/{invoiceId}/update-payment-status")
    public ResponseEntity<String> updatePaymentStatus(
            @PathVariable String invoiceId,
            @RequestBody Map<String, String> payload) {
        
        String newStatus = payload.get("paymentStatus");
        invoiceService.updatePaymentStatus(invoiceId, newStatus);
        return ResponseEntity.ok("Payment status updated");
    }
    
    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<?> deleteInvoice(@PathVariable String invoiceId) {
        boolean isDeleted = invoiceService.deleteInvoiceById(invoiceId);
        if (isDeleted) {
            return ResponseEntity.ok().body("Invoice deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Invoice not found");
        }
    }
    

}

