package com.invoice.billing_system.repository.product_repo;

import com.invoice.billing_system.model.product.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
	
		Optional<Product> findTopByOrderByProductIdDesc(); // For ID generation
//	    List<Product> findByPlant_PlantId(String plantId);
}

