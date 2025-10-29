package com.invoice.billing_system.model.invoice_dto;

import java.time.LocalDate;
import java.util.List;

public class DistributorClientInvoiceDTO {
    private String invoiceId;
    private String clientId;
    private String clientName;
    private LocalDate invoiceDate;
    private List<ProductSummaryDTO> products;
    private double totalAmount;
    private double totalPay;
    private double globalDiscount;
    
   
    private double globalTax;
    
    private String paymentStatus;
    
    // Getters and setters
    public String getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    	
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
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
	public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public List<ProductSummaryDTO> getProducts() {
        return products;
    }
    public void setProducts(List<ProductSummaryDTO> products) {
        this.products = products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalPay() {
        return totalPay;
    }
    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }
}

