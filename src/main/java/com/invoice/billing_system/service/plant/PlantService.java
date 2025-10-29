package com.invoice.billing_system.service.plant;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.billing_system.model.delivery.DeliveryNote;
import com.invoice.billing_system.model.plant.Plant;
import com.invoice.billing_system.model.plant.PlantStock;
import com.invoice.billing_system.model.plant_dto.PlantStockDto;
import com.invoice.billing_system.model.plant_dto.PlantStockViewDTO;
import com.invoice.billing_system.model.product.Product;
import com.invoice.billing_system.repository.delivery.DeliveryNoteRepository;
import com.invoice.billing_system.repository.plant.PlantRepository;
import com.invoice.billing_system.repository.plant.PlantStockRepository;
import com.invoice.billing_system.repository.product_repo.ProductRepository;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private  PlantStockRepository plantStockRepository;
    
    @Autowired
    private DeliveryNoteRepository deliveryNoteRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public Plant addPlant(Plant plant) {
        if (plant.getPlantId() == null || plant.getPlantId().isEmpty()) {
            String prefix = generatePrefix(plant.getName());
            long count = plantRepository.findAll().stream()
                    .filter(p -> p.getPlantId().startsWith(prefix))
                    .count() + 1;
            String id = String.format("%s/%04d", prefix, count);
            plant.setPlantId(id);
        }
        return plantRepository.save(plant);
    }

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public void deletePlant(String plantId) {
        plantRepository.deleteById(plantId);
    }

    public Plant getPlantById(String id) {
        return plantRepository.findById(id).orElse(null);
    }

    private String generatePrefix(String name) {
        String[] words = name.trim().split("\\s+");
        if (words.length == 1) {
            return name.substring(0, 3).toUpperCase();
        } else {
            StringBuilder prefix = new StringBuilder();
            for (String word : words) {
                if (!word.isEmpty()) {
                    prefix.append(Character.toUpperCase(word.charAt(0)));
                }
            }
            return prefix.toString();
        }
    }
    
    public List<PlantStockDto> getPlantsByProduct(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        List<PlantStock> plantStocks = plantStockRepository.findByProduct(product);

        return plantStocks.stream().map(stock -> {
        	PlantStockDto dto = new PlantStockDto();
            dto.setPlantId(stock.getPlant().getPlantId());
            dto.setPlantName(stock.getPlant().getName());
            dto.setProductId(product.getProductId());
            dto.setProductName(product.getName());
            dto.setAvailableStock(stock.getStockQuantity());
            return dto;
        }).collect(Collectors.toList());
    }
    
    public List<PlantStockViewDTO> getStocksByPlant(String plantId) {
        List<PlantStock> stocks = plantStockRepository.findByPlant_PlantId(plantId);

        return stocks.stream()
                .map(ps -> new PlantStockViewDTO(
                        ps.getProduct().getProductId(),
                        ps.getProduct().getName(),
                        ps.getStockQuantity(),
                        ps.getProduct().getMfgDate() // using mfgDate from product
                ))
                .collect(Collectors.toList());
    }
    
    public List<DeliveryNote> getDeliveryNotesByPlantName(String plantName) {
        return deliveryNoteRepository.findByPlantName(plantName);
    }
    
}