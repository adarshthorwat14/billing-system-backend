package com.invoice.billing_system.service.logistic_serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.billing_system.model.logistic.LogisticDepartment;
import com.invoice.billing_system.repository.logistic_repo.LogisticDepartmentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class LogisticDepartmentService {

    @Autowired
    private LogisticDepartmentRepository repository;

    // Generate next ID like LD10001
    private String generateNextId() {
        List<LogisticDepartment> all = repository.findAll();
        int max = 10000;
        for (LogisticDepartment ld : all) {
            try {
                int num = Integer.parseInt(ld.getId().substring(2));
                if (num > max) max = num;
            } catch (Exception ignored) {}
        }
        return "LD" + (max + 1);
    }

    // Generate plant code like PUNX0001
    private String generatePlantCode(String plantName) {
        String prefix = plantName.toUpperCase().substring(0, 3); // PUN
        String baseCode = prefix + "X";
        int count = 1;
        String code;

        do {
            code = baseCode + String.format("%04d", count);
            count++;
        } while (repository.existsByPlantCode(code));

        return code;
    }

    public LogisticDepartment create(LogisticDepartment input) {
        input.setId(generateNextId());
        input.setPlantCode(generatePlantCode(input.getPlantName()));
        input.setCreatedDate(LocalDate.now());
        return repository.save(input);
    }

    public List<LogisticDepartment> getAll() {
        return repository.findAll();
    }

    public LogisticDepartment getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public LogisticDepartment update(String id, LogisticDepartment updated) {
        LogisticDepartment existing = repository.findById(id).orElse(null);
        if (existing == null) return null;

        // Only updatable fields
        existing.setPhone(updated.getPhone());
        existing.setStatus(updated.isStatus());
        existing.setPassword(updated.getPassword());

        return repository.save(existing);
    }
    
    public LogisticDepartment login(String id, String password) {
        LogisticDepartment logistic = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Logistic ID not found"));

        if (!logistic.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        if (!logistic.isStatus()) {
            throw new RuntimeException("Account is inactive");
        }

        return logistic;
    }
}
