package com.invoice.billing_system.repository.plant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invoice.billing_system.model.plant.Plant;
import com.invoice.billing_system.model.plant.PlantStock;
import com.invoice.billing_system.model.product.Product;

@Repository
public interface PlantStockRepository extends JpaRepository<PlantStock, Long> {

	List<PlantStock> findByProduct(Product product);
    List<PlantStock> findByPlant(Plant plant);
    Optional<PlantStock> findByProductAndPlant(Product product, Plant plant);
    List<PlantStock> findByPlant_PlantId(String plantId);
    Optional<PlantStock> findByProduct_ProductIdAndPlant_PlantId(String productId, String plantId);
}
