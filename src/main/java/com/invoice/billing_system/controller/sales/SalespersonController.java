package com.invoice.billing_system.controller.sales;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.billing_system.model.admin_dto.LoginRequest;
import com.invoice.billing_system.model.client.Client;
import com.invoice.billing_system.model.sales.Salesperson;
import com.invoice.billing_system.service.sales.SalespersonService;

@RestController
@RequestMapping("/api/salespersons")
public class SalespersonController {

    private final SalespersonService service;

    public SalespersonController(SalespersonService service) {
        this.service = service;
    }

    @PostMapping
    public Salesperson createSalesperson(@RequestBody Salesperson salesperson) {
        return service.createSalesperson(salesperson);
    }

    @GetMapping
    public List<Salesperson> getAllSalespersons() {
        return service.getAllSalespersons();
    }

    @GetMapping("/{id}")
    public Salesperson getSalespersonById(@PathVariable Long id) {
        return service.getSalespersonById(id);
    }

    @PutMapping("/{id}")
    public Salesperson updateSalesperson(@PathVariable Long id, @RequestBody Salesperson salesperson) {
        return service.updateSalesperson(id, salesperson);
    }

    @DeleteMapping("/{id}")
    public void deleteSalesperson(@PathVariable Long id) {
        service.deleteSalesperson(id);
    }
    
    @PostMapping("/login")
    public Salesperson login(@RequestBody Map<String, String> credentials) {
        Long id = Long.parseLong(credentials.get("id"));
        String password = credentials.get("password");
        return service.login(id, password);
    }
}
