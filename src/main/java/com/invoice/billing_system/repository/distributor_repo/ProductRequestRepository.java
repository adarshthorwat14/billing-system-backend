package com.invoice.billing_system.repository.distributor_repo;

import com.invoice.billing_system.model.distributor.ProductRequest;
import com.invoice.billing_system.model.distributor.ProductRequestItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRequestRepository extends JpaRepository<ProductRequest, String> {
//    List<ProductRequest> findByDistributorId(String distributorId);
	List<ProductRequest> findByDistributor_Id(String distributorId);
    Optional<ProductRequest> findTopByOrderByRequestIdDesc();
    Optional<ProductRequest> findById(String requestId);
    long countByStatus(String status);
//	List<ProductRequestItem> getRequestsByDistributor(String distributorId);
//    List<ProductRequestItem> findApprovedItemsByDistributor(String distributorId);
    
    @Query("SELECT r FROM ProductRequest r WHERE r.distributor.id = :distributorId AND r.status = 'APPROVED'")
    List<ProductRequest> findApprovedRequestsByDistributorId(@Param("distributorId") String distributorId);
    
    
    @Query("SELECT pri FROM ProductRequest pr " +
    	       "JOIN pr.requestItems pri " +
    	       "WHERE pr.distributor.id = :distributorId " +
    	       "AND pri.product.productId = :productId " +
    	       "AND pri.status = 'APPROVED' " +
    	       "AND pri.approvedQuantity > pri.quantityUsed " +
    	       "ORDER BY pri.id")
    	List<ProductRequestItem> findAvailableStockForProductAndDistributor(
    	    @Param("distributorId") String distributorId,
    	    @Param("productId") String productId
    	);
}
