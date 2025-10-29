package com.invoice.billing_system.model.product_dto;

import java.time.LocalDate;
import java.util.List;

import com.invoice.billing_system.model.plant_dto.PlantStockItemDTO;

public class ProductCreateDTO {
    private String name;
    private String description;
    private Double price;
    private String img;
    private String batchNumber;
    private LocalDate expiryDate;
    private String hsnCode;      // we’ll resolve HSN by code
    private LocalDate mfgDate;
    private String unitOfMeasure;
    private Double rdp;
    private Double mrp;
    
    private List<PlantStockItemDTO> plantStocks;
    
    
	public List<PlantStockItemDTO> getPlantStocks() {
		return plantStocks;
	}
	public void setPlantStocks(List<PlantStockItemDTO> plantStocks) {
		this.plantStocks = plantStocks;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public LocalDate getMfgDate() {
		return mfgDate;
	}
	public void setMfgDate(LocalDate mfgDate) {
		this.mfgDate = mfgDate;
	}
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	public Double getRdp() {
		return rdp;
	}
	public void setRdp(Double rdp) {
		this.rdp = rdp;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
    
    
    

}