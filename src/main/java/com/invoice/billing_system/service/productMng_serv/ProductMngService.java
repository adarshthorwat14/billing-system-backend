package com.invoice.billing_system.service.productMng_serv;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.invoice.billing_system.model.admin.Admin;
import com.invoice.billing_system.model.product_mng.ProductManagement;
import com.invoice.billing_system.repository.productMng_repo.ProductMngRepository;

@Service

public class ProductMngService {
	
	@Autowired
	private ProductMngRepository productMngRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// Create or Update a Client
	public ProductManagement saveProductMng(ProductManagement productManagement) {
		if (productManagement.getId() == null || productManagement.getId().isEmpty()) {
			Long nextVal = jdbcTemplate.queryForObject("SELECT nextval('productmng_id_seq')", Long.class);
			String formattedId = String.format("PRM%05d", nextVal);
			productManagement.setId(formattedId);
		}
		return productMngRepository.save(productManagement);
	}

    public ProductManagement login(String email,String password) {
    	ProductManagement productManagement = productMngRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));

        if (!productManagement.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return productManagement;
    }
	// Get all Clients
	public List<ProductManagement> getAllProductMng() {
		return productMngRepository.findAll();
	}

	// Get a Client by ID
	public Optional<ProductManagement> getProductMngById(String id) {
		return productMngRepository.findById(id);
	}
	
	 public Optional<ProductManagement> updatedProductMng(String id, ProductManagement updatedProductMng) {
	        return productMngRepository.findById(id).map(existingProductMng -> {
	        	existingProductMng.setName(updatedProductMng.getName());
	        	existingProductMng.setEmail(updatedProductMng.getEmail());
	        	existingProductMng.setPassword(updatedProductMng.getPassword());
	        	existingProductMng.setRole(updatedProductMng.getRole());
	            
	        	existingProductMng.setUsername(updatedProductMng.getUsername());
	        	existingProductMng.setStatus(updatedProductMng.isStatus());
	            return productMngRepository.save(updatedProductMng);
	        });
	    }

	// Delete a Client by ID
	public void deleteProductMng(String id) {
		productMngRepository.deleteById(id);
	}
}
