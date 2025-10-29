package com.invoice.billing_system.service.logistic_serv;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.invoice.billing_system.model.logistic.LogisticVehicle;
import com.invoice.billing_system.repository.logistic_repo.LogisticVehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LogisticVehicleService {

    private final LogisticVehicleRepository vehicleRepository;

    @Autowired
    public LogisticVehicleService(LogisticVehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public LogisticVehicle saveVehicle(LogisticVehicle vehicle) {
        if (vehicle.getVehicleId() == null || vehicle.getVehicleId().isEmpty()) {
            vehicle.setVehicleId(generateVehicleId());
        }
        return vehicleRepository.save(vehicle);
    }

    public List<LogisticVehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<LogisticVehicle> getVehicleById(String id) {
        return vehicleRepository.findById(id);
    }

    public void deleteVehicle(String id) {
        vehicleRepository.deleteById(id);
    }

    public boolean vehicleNumberExists(String vehicleNumber) {
        return vehicleRepository.existsByVehicleNumber(vehicleNumber);
    }

    // Generate ID like VH00001
    private String generateVehicleId() {
        List<LogisticVehicle> allVehicles = vehicleRepository.findAll();
        int max = 0;

        for (LogisticVehicle v : allVehicles) {
            String id = v.getVehicleId();
            if (id != null && id.startsWith("VH")) {
                try {
                    int num = Integer.parseInt(id.substring(2));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        }

        int next = max + 1;
        return String.format("VH%05d", next);
    }
    
    public LogisticVehicle updateVehicle(String vehicleId, LogisticVehicle vehicle) {
        LogisticVehicle existing = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        existing.setVehicleNumber(vehicle.getVehicleNumber());
        existing.setVehicleType(vehicle.getVehicleType());
        existing.setDriverName(vehicle.getDriverName());
        existing.setDriverPhone(vehicle.getDriverPhone());
        existing.setCapacity(vehicle.getCapacity());
        existing.setStatus(vehicle.getStatus());
        existing.setAssignedPlant(vehicle.getAssignedPlant());
        existing.setLastMaintenanceDate(vehicle.getLastMaintenanceDate());

        return vehicleRepository.save(existing);
    }
    
    public List<LogisticVehicle> getAvailableVehicles() {
        return vehicleRepository.findByStatus("ACTIVE");
    }
}
