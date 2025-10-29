package com.invoice.billing_system.model.plant_dto;

public class PlantStockItemDTO {
    private String plantId;
    private Integer stockQuantity;

    public String getPlantId() { return plantId; }
    public void setPlantId(String plantId) { this.plantId = plantId; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }
}
