package com.invoice.billing_system.model.invoice_dto;

public class InvoiceItemRequest {

    private String productId;         // Identifier for the product

    private int quantity;             // Quantity of the product
    private double price;             // Price per unit of the product

    private double taxRate;           // Tax rate applied to the item (%)
    private double discount;          // Discount applied to the item (%)

    private String description;       // Optional description of the item

    // === Getters and Setters ===

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}