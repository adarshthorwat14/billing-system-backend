package com.invoice.billing_system.repository.plant;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.billing_system.model.plant.PlantEmployee;



public interface PlantEmployeeRepository extends JpaRepository<PlantEmployee, String> {
	
	Optional<PlantEmployee> findByEmail(String email);
}
