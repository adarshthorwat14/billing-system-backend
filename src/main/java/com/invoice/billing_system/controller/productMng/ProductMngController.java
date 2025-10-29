package com.invoice.billing_system.controller.productMng;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.billing_system.model.admin.Admin;
import com.invoice.billing_system.model.admin_dto.LoginRequest;
import com.invoice.billing_system.model.product_mng.ProductManagement;

import com.invoice.billing_system.repository.productMng_repo.ProductMngRepository;

import com.invoice.billing_system.service.productMng_serv.ProductMngService;

@RestController
@RequestMapping("/api/productMng")
public class ProductMngController {

	@Autowired
	private ProductMngService productMngService;
	
    private final ProductMngRepository productMngRepository;

    @Autowired
    public ProductMngController(ProductMngRepository productMngRepository) {
        this.productMngRepository = productMngRepository;
    }
	
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getProductMngCount() {
        long count = productMngRepository.count();
        return ResponseEntity.ok(Collections.singletonMap("count", count));
    }
    
	// Create or Update a Client
	@PostMapping
	public ResponseEntity<ProductManagement> createproductMng(@RequestBody ProductManagement productMng) {
		ProductManagement savedProductMng = productMngService.saveProductMng(productMng);
		return new ResponseEntity<>(savedProductMng, HttpStatus.CREATED);
	}
	

	
	@PostMapping("/login")
    public ResponseEntity<ProductManagement> login(@RequestBody LoginRequest request) {
        ProductManagement productManagement = productMngService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(productManagement);
    }

	// Get all Clients
	@GetMapping
	public ResponseEntity<List<ProductManagement>> getAllProductMng() {
		List<ProductManagement> productMng = productMngService.getAllProductMng();
		return new ResponseEntity<>(productMng, HttpStatus.OK);
	}

	// Get a Client by ID
	@GetMapping("/{id}")
	public ResponseEntity<ProductManagement> getProductMngById(@PathVariable String id) {
		Optional<ProductManagement> productMng = productMngService.getProductMngById(id);
		return productMng.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductManagement> updateClient(@PathVariable String id, @RequestBody ProductManagement updatedProductMng) {
	    return productMngService.updatedProductMng(id, updatedProductMng)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	}

	// Delete a Client by ID
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProductMng(@PathVariable String id) {
		productMngService.deleteProductMng(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
