package com.invoice.billing_system.model.hsn;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Hsn {
	
	@Id
    private String code; // e.g. "84145110"

    private String description; // e.g. "Electric fan with motor"
    private double gstRate;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getGstRate() {
		return gstRate;
	}
	public void setGstRate(double gstRate) {
		this.gstRate = gstRate;
	}
	
	
	public Hsn(String code, String description, double gstRate) {
		super();
		this.code = code;
		this.description = description;
		this.gstRate = gstRate;
	}
	

    
    public Hsn() {
		// TODO Auto-generated constructor stub
	}
    
}
