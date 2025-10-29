package com.invoice.billing_system.repository.productMng_repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.billing_system.model.product_mng.ProductManagement;

public interface ProductMngRepository extends JpaRepository<ProductManagement, String> {
	Optional<ProductManagement> findByEmail(String email);

}
