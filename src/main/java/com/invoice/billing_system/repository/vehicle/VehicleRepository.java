package com.invoice.billing_system.repository.vehicle;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.billing_system.model.delivery.DeliveryNote;
import com.invoice.billing_system.model.vehicle.VehicleRequestPlant;

public interface VehicleRepository extends JpaRepository<VehicleRequestPlant, String> {

	List<VehicleRequestPlant> findByStatus(String status);
	
	Optional<VehicleRequestPlant> findByTransportId(String transportId);

	List<VehicleRequestPlant> findAll();
	
	List<VehicleRequestPlant> findByStatusNot(String status);

}
