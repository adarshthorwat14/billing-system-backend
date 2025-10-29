package com.invoice.billing_system.repository.logistic_repo;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.invoice.billing_system.model.logistic.LogisticVehicle;

@Repository
public interface LogisticVehicleRepository extends JpaRepository<LogisticVehicle, String> {
	boolean existsByVehicleNumber(String vehicleNumber);
	Optional<LogisticVehicle> findByVehicleNumber(String vehicleNumber);
	
	 List<LogisticVehicle> findByStatus(String status);
}
