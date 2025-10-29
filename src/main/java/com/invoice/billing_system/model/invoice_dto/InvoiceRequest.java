package com.invoice.billing_system.model.invoice_dto;

import java.time.LocalDate;
import java.util.List;

public class InvoiceRequest {

    private String clientId;

    private LocalDate invoiceDate;
    private LocalDate dueDate;

    private String paymentStatus;     // e.g., PAID, UNPAID, PARTIALLY_PAID
    private String paymentMethod;     // e.g., CASH, CARD, BANK_TRANSFER

    private double globalDiscount;    // Global discount for the entire invoice
    private double globalTax;         // Global tax for the entire invoice

    private String notes;             // Additional notes for the invoice
    private String generatedBy;       // User who generated the invoice
    private String distributorId;
    
    private String plantId;
    
    private List<InvoiceItemRequest> invoiceItems; // List of items included in the invoice

    // === Getters and Setters ===

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    
    public String getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
	}

	public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    
    public String getPlantId() {
		return plantId;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}

	public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getGlobalDiscount() {
        return globalDiscount;
    }

    public void setGlobalDiscount(double globalDiscount) {
        this.globalDiscount = globalDiscount;
    }

    public double getGlobalTax() {
        return globalTax;
    }

    public void setGlobalTax(double globalTax) {
        this.globalTax = globalTax;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }

    public List<InvoiceItemRequest> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItemRequest> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
}