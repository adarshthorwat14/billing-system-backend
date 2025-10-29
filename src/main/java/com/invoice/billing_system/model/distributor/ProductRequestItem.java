package com.invoice.billing_system.model.distributor;

import com.invoice.billing_system.model.product.Product;
import jakarta.persistence.*;


@Entity

public class ProductRequestItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private ProductRequest productRequest;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantityRequested;
    private String unit;
    private String status; // e.g., PENDING, APPROVED, REJECTED
    private String description;
    
    @Column(name = "approved_quantity")
    private int approvedQuantity;
    
    private int quantityUsed = 0;
    
	public int getApprovedQuantity() {
		return approvedQuantity;
	}
	public void setApprovedQuantity(int approvedQuantity) {
		this.approvedQuantity = approvedQuantity;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProductRequest getProductRequest() {
		return productRequest;
	}
	public void setProductRequest(ProductRequest productRequest) {
		this.productRequest = productRequest;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantityRequested() {
		return quantityRequested;
	}
	public void setQuantityRequested(int quantityRequested) {
		this.quantityRequested = quantityRequested;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public int getQuantityUsed() {
		return quantityUsed;
	}
	public void setQuantityUsed(int quantityUsed) {
		this.quantityUsed = quantityUsed;
	}
	
	public ProductRequestItem(Long id, ProductRequest productRequest, Product product, int quantityRequested,
			String unit, String status, String description,int quantityUsed) {
		super();
		this.id = id;
		this.productRequest = productRequest;
		this.product = product;
		this.quantityRequested = quantityRequested;
		this.unit = unit;
		this.status = status;
		this.description = description;
		this.quantityUsed = quantityUsed;
	}
    
	public ProductRequestItem() {
		// TODO Auto-generated constructor stub
	}
    
}
