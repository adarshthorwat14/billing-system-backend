package com.invoice.billing_system.repository.distributor_repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.invoice.billing_system.model.distributor.ProductRequestItem;

import java.util.List;

public interface ProductRequestItemRepositorynew extends JpaRepository<ProductRequestItem, String> {

	 @Query("SELECT i FROM ProductRequestItem i " +
	           "WHERE i.product.productId = :productId " +
	           "AND i.productRequest.distributor.id = :distributorId " +
	           "AND i.status = 'APPROVED' " +
	           "AND i.approvedQuantity > i.quantityUsed " +
	           "ORDER BY i.id ASC")
	    List<ProductRequestItem> findAvailableStockForProductAndDistributor(String distributorId, String productId);
}
