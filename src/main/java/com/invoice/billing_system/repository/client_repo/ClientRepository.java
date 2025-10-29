package com.invoice.billing_system.repository.client_repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.invoice.billing_system.model.client.Client;
import com.invoice.billing_system.model.distributor.Distributor;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
	// Custom queries can be added here if needed
	
	@Query("SELECT c FROM Client c WHERE :distributorId IN elements(c.distributorId)")
    List<Client> findByDistributor(String distributorId);
	Optional<Client> findById(String id);
}
