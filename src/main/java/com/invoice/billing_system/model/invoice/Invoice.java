package com.invoice.billing_system.model.invoice;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.invoice.billing_system.model.client.Client;
import com.invoice.billing_system.model.distributor.Distributor;
import com.invoice.billing_system.model.plant.Plant;

@Entity
public class Invoice {

    @Id
    private String invoiceId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private LocalDate invoiceDate;

    private LocalDate dueDate;

    private String paymentStatus; // e.g., PAID, UNPAID, PARTIALLY_PAID

    private String paymentMethod; // e.g., CASH, CARD, BANK_TRANSFER

    private double totalAmount;

    private double taxAmount;

    private double discount;
    
    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private Distributor distributor;
	

    @Column(length = 1000)
    private String notes;

    private String generatedBy;

    private double globalDiscount;
    
    private double globalTax;
    
    private double totalPay;
    
    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;
    
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<InvoiceItem> invoiceItems;

    // Constructors
    public Invoice() {}

 

    public Invoice(String invoiceId, Client client, LocalDate invoiceDate, LocalDate dueDate, String paymentStatus,
			String paymentMethod, double totalAmount, double taxAmount, double discount, String notes,
			String generatedBy, double globalDiscount,Plant plant, double globalTax,double totalPay, List<InvoiceItem> invoiceItems) {
		super();
		this.invoiceId = invoiceId;
		this.client = client;
		this.invoiceDate = invoiceDate;
		this.dueDate = dueDate;
		this.paymentStatus = paymentStatus;
		this.paymentMethod = paymentMethod;
		this.totalAmount = totalAmount;
		this.taxAmount = taxAmount;
		this.discount = discount;
		this.notes = notes;
		this.generatedBy = generatedBy;
		this.globalDiscount = globalDiscount;	
		this.globalTax = globalTax;
		this.totalPay = totalPay;
		this.invoiceItems = invoiceItems;
		this.plant = plant;
	}



	public String getInvoiceId() {
		return invoiceId;
	}



	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}



	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
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



	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	

	public Plant getPlant() {
		return plant;
	}



	public void setPlant(Plant plant) {
		this.plant = plant;
	}



	public String getPaymentStatus() {
		return paymentStatus;
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



	public double getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}



	public double getTaxAmount() {
		return taxAmount;
	}



	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}



	public double getDiscount() {
		return discount;
	}



	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}


	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}


	public String getGeneratedBy() {
		return generatedBy;
	}


	public void setGeneratedBy(String generatedBy) {
		this.generatedBy = generatedBy;
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



	public double getTotalPay() {
		return totalPay;
	}



	public void setTotalPay(double totalPay) {
		this.totalPay = totalPay;
	}



	public List<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}



	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}



    
   }
