package com.invoice.billing_system.repository.distributor_repo;

import com.invoice.billing_system.model.distributor.Distributor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, String> {
	
	Optional<Distributor> findByEmail(String email);
	Optional<Distributor> findTopByOrderByIdDesc();
  
}

