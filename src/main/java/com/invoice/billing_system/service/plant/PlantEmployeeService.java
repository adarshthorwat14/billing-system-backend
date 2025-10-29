package com.invoice.billing_system.service.plant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.billing_system.model.plant.Plant;
import com.invoice.billing_system.model.plant.PlantEmployee;
import com.invoice.billing_system.model.plant_dto.PlantEmployeeLoginResponseDTO;
import com.invoice.billing_system.repository.plant.PlantEmployeeRepository;
import com.invoice.billing_system.repository.plant.PlantRepository;

@Service
public class PlantEmployeeService {

    @Autowired
    private PlantEmployeeRepository repository;

    @Autowired
    private PlantRepository plantRepository;

    public String generateEmployeeId() {
        long count = repository.count() + 1;
        return String.format("PLEMP%04d", count);
    }

    public PlantEmployee saveEmployee(PlantEmployee employee) {
        employee.setEmployeeId(generateEmployeeId());

        // Get plant name
        Plant plant = plantRepository.findById(employee.getPlantId())
            .orElseThrow(() -> new RuntimeException("Plant not found"));

        employee.setPlantName(plant.getName());

        // Generate email
        String[] parts = employee.getEmployeeName().trim().split(" ");
        if (parts.length >= 2) {
            employee.setEmail(parts[1].toLowerCase() + "." + parts[0].toLowerCase() + "@beunique.com");
        } else {
            employee.setEmail(parts[0].toLowerCase() + "@beunique.com");
        }

        return repository.save(employee);
    }

    public List<PlantEmployee> getAll() {
        return repository.findAll();
    }
    
    public PlantEmployeeLoginResponseDTO login(String email, String password) {
        PlantEmployee emp = repository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Email not found"));

        if (!emp.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }


        PlantEmployeeLoginResponseDTO response = new PlantEmployeeLoginResponseDTO();
        response.setId(emp.getEmployeeId());
        response.setName(emp.getEmployeeName());
        response.setEmail(emp.getEmail());
        response.setPosition(emp.getPosition());
        response.setPlantId(emp.getPlantId());
        response.setPlantName(emp.getPlantName());

        return response;
    }
}

