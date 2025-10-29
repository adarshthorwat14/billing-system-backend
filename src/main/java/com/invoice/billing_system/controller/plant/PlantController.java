package com.invoice.billing_system.controller.plant;

import com.invoice.billing_system.model.delivery.DeliveryNote;
import com.invoice.billing_system.model.plant.Plant;
import com.invoice.billing_system.model.plant_dto.PlantDTO;
import com.invoice.billing_system.model.plant_dto.PlantStockDto;
import com.invoice.billing_system.model.plant_dto.PlantStockViewDTO;
import com.invoice.billing_system.repository.plant.PlantRepository;
import com.invoice.billing_system.service.plant.PlantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/plants")
public class PlantController {

    @Autowired
    private PlantService plantService;
    
    @Autowired
    private PlantRepository plantRepository;
      
    @PostMapping
    public Plant addPlant(@RequestBody Plant plant) {
        return plantService.addPlant(plant);
    }

    @GetMapping
    public List<PlantDTO> getAllPlants() {
        return plantRepository.findAll()
                .stream()
                .map(plant -> new PlantDTO(
                        plant.getPlantId(),
                        plant.getName(),
                        plant.getLocation(),
                        plant.getDescription()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Plant getPlant(@PathVariable String id) {
        return plantService.getPlantById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePlant(@PathVariable String id) {
        plantService.deletePlant(id);
    }
    
    @GetMapping("/plant")
    public ResponseEntity<List<PlantStockViewDTO>> getStockByPlant(@RequestParam String plantId) {
        List<PlantStockViewDTO> stocks = plantService.getStocksByPlant(plantId);
        return ResponseEntity.ok(stocks);
    }
    
    @GetMapping("/plant-stock/by-product/{productId}")
    public ResponseEntity<List<PlantStockDto>> getPlantsByProduct(@PathVariable String productId) {
        return ResponseEntity.ok(plantService.getPlantsByProduct(productId));
    }
    
    @GetMapping("/{plantName}/delivery-notes")
    public ResponseEntity<List<DeliveryNote>> getDeliveryNotesByPlantName(@PathVariable String plantName) {
        return ResponseEntity.ok(plantService.getDeliveryNotesByPlantName(plantName));
    }

}

