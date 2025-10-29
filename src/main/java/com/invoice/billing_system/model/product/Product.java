package com.invoice.billing_system.model.product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.invoice.billing_system.model.hsn.Hsn;
import com.invoice.billing_system.model.plant.Plant;
import com.invoice.billing_system.model.plant.PlantStock;

@Entity
public class Product {
	
	@Id
    private String productId; // e.g., PRD001

    private String name;
    private String description;

    private double price;
    private double mrp;
    private double rdp;
    
    private String unitOfMeasure; // e.g., "pcs", "kg", "litre"

    @ManyToOne
    @JoinColumn(name = "hsn_code")
    private Hsn hsn;
    
    
    private String batchNumber;
     
    private LocalDate mfgDate;
    private LocalDate expiryDate;

    private String barcode;

    private String img; // Product image URL or filename

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public double getRdp() {
		return rdp;
	}

	public void setRdp(double rdp) {
		this.rdp = rdp;
	}


	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public LocalDate getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(LocalDate mfgDate) {
		this.mfgDate = mfgDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Hsn getHsn() {
		return hsn;
	}

	public void setHsn(Hsn hsn) {
		this.hsn = hsn;
	}


    }
