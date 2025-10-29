package com.invoice.billing_system.controller.plant;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.billing_system.model.plant.PlantEmployee;
import com.invoice.billing_system.model.plant_dto.PlantEmployeeLoginDTO;
import com.invoice.billing_system.model.plant_dto.PlantEmployeeLoginResponseDTO;
import com.invoice.billing_system.repository.plant.PlantEmployeeRepository;
import com.invoice.billing_system.service.plant.PlantEmployeeService;

@RestController
@RequestMapping("/api/plant-employees")
public class PlantEmployeeController {

    @Autowired
    private PlantEmployeeService service;
    
    @Autowired
    private PlantEmployeeRepository plantEmployeeRepository;
    
    @PostMapping
    public ResponseEntity<PlantEmployee> create(@RequestBody PlantEmployee employee) {
        PlantEmployee saved = service.saveEmployee(employee);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<PlantEmployee>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody PlantEmployeeLoginDTO dto) {
        try {
            PlantEmployeeLoginResponseDTO response = service.login(dto.getEmail(), dto.getPassword());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}

