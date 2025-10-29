package com.invoice.billing_system.repository.plant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.billing_system.model.plant.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, String> {

	  List<Plant> findByPlantIdIn(List<String> plantIds);
	
}
