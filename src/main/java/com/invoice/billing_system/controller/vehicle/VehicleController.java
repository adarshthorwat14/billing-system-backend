package com.invoice.billing_system.controller.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.billing_system.model.dispatch_dto.ApprovedVehicleRequestDTO;
import com.invoice.billing_system.model.logistic.LogisticVehicle;
import com.invoice.billing_system.model.vehicle.VehicleRequestPlant;
import com.invoice.billing_system.service.vehicle.VehicleService;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
	
	private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/vehicle-requests/create")
    public ResponseEntity<VehicleRequestPlant> create(@RequestBody VehicleRequestPlant request) {
        VehicleRequestPlant	savedRequest = vehicleService.requestVehicle(request);
        return ResponseEntity.ok(savedRequest);
    }
    
    @GetMapping("/vehicle-requests")
    public ResponseEntity<List<VehicleRequestPlant>> getAllRequests() {
        return ResponseEntity.ok(vehicleService.getAllRequests());
    }

    @GetMapping("/pending/request")
    public ResponseEntity<List<VehicleRequestPlant>> getPendingRequests() {
        return ResponseEntity.ok(vehicleService.getAllPendingRequests());
    }
    
    @PostMapping("/vehicle-requests/approve")
    public ResponseEntity<String> approveRequest(@RequestParam String transportId) {
        System.out.println("Approving: " + transportId);
        vehicleService.approveRequest(transportId);
        return ResponseEntity.ok("Approved");
    }
    
    @GetMapping("/approved")
    public ResponseEntity<List<ApprovedVehicleRequestDTO>> getApprovedRequests() {
        return ResponseEntity.ok(vehicleService.getApprovedRequests());
    }
    
    @GetMapping("/status/approved")
    public ResponseEntity<List<VehicleRequestPlant>> getAllApprovedRequests() {
        return ResponseEntity.ok(vehicleService.getAllApprovedRequests());
    }

}
