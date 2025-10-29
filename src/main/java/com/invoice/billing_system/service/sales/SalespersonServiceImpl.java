package com.invoice.billing_system.service.sales;

import java.util.List;

import org.springframework.stereotype.Service;

import com.invoice.billing_system.model.sales.Salesperson;
import com.invoice.billing_system.repository.sales.SalespersonRepository;

@Service
public class SalespersonServiceImpl implements SalespersonService {

    private final SalespersonRepository repository;

    public SalespersonServiceImpl(SalespersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Salesperson createSalesperson(Salesperson salesperson) {
        return repository.save(salesperson);
    }

    @Override
    public List<Salesperson> getAllSalespersons() {
        return repository.findAll();
    }

    @Override
    public Salesperson getSalespersonById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Salesperson not found"));
    }

    @Override
    public Salesperson updateSalesperson(Long id, Salesperson salesperson) {
        Salesperson existing = getSalespersonById(id);
        existing.setName(salesperson.getName());
        existing.setPhoneNumber(salesperson.getPhoneNumber());
        existing.setEmail(salesperson.getEmail());
        existing.setPosition(salesperson.getPosition());
        existing.setState(salesperson.getState());
        existing.setRegion(salesperson.getRegion());
        existing.setTerritory(salesperson.getTerritory());
        existing.setTarget(salesperson.getTarget());
        existing.setAchieved(salesperson.getAchieved());
        existing.setStatus(salesperson.getStatus());
        return repository.save(existing);
    }

    @Override
    public void deleteSalesperson(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    public Salesperson login(Long id, String password) {
        Salesperson salesperson = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salesperson not found with id: " + id));

        if (!salesperson.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return salesperson;
    }
}
