package com.invoice.billing_system.controller.logistic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.invoice.billing_system.model.logistic.LogisticVehicle;
import com.invoice.billing_system.service.logistic_serv.LogisticVehicleService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/logistics/vehicles")
public class LogisticVehicleController {

    private final LogisticVehicleService vehicleService;

    @Autowired
    public LogisticVehicleController(LogisticVehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // ✅ Create new vehicle (ID auto-generated in service)
    @PostMapping
    public ResponseEntity<LogisticVehicle> createVehicle(@RequestBody LogisticVehicle vehicle) {
        if (vehicleService.vehicleNumberExists(vehicle.getVehicleNumber())) {
            return ResponseEntity.badRequest().body(null);
        }
        LogisticVehicle saved = vehicleService.saveVehicle(vehicle);
        return ResponseEntity.ok(saved);
    }

    // ✅ Get all vehicles
    @GetMapping
    public ResponseEntity<List<LogisticVehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    // ✅ Get single vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<LogisticVehicle> getVehicleById(@PathVariable String id) {
        Optional<LogisticVehicle> vehicle = vehicleService.getVehicleById(id);
        return vehicle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Delete vehicle by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String id) {
        if (vehicleService.getVehicleById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    // ✅ Optional: Check if vehicle number already exists (for validation)
    @GetMapping("/exists/{vehicleNnumber}")
    public ResponseEntity<Boolean> checkIfNumberExists(@PathVariable String vehicleNnumber) {
        return ResponseEntity.ok(vehicleService.vehicleNumberExists(vehicleNnumber));
    }
    
    @PutMapping("/update/{vehicleId}")
    public ResponseEntity<LogisticVehicle> updateVehicle(@PathVariable String vehicleId,
                                                         @RequestBody LogisticVehicle updatedVehicle) {
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicleId, updatedVehicle));
    }
    
    @GetMapping("/available")
    public List<LogisticVehicle> getAvailableVehicles() {
        return vehicleService.getAvailableVehicles();
    }
}