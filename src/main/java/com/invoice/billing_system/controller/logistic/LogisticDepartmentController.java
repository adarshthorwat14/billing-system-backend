package com.invoice.billing_system.controller.logistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.invoice.billing_system.model.admin_dto.LoginRequest;
import com.invoice.billing_system.model.delivery.DeliveryNote;
import com.invoice.billing_system.model.logistic.LogisticDepartment;
import com.invoice.billing_system.service.delivery.DeliveryNoteService;
import com.invoice.billing_system.service.logistic_serv.LogisticDepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/logistics")
public class LogisticDepartmentController {

    @Autowired
    private LogisticDepartmentService service;

    	
    private final DeliveryNoteService deliveryNoteService;

    @Autowired
    public LogisticDepartmentController(DeliveryNoteService deliveryNoteService) {
        this.deliveryNoteService = deliveryNoteService;
    }
    
    @GetMapping("/delivery-notes")
    public List<DeliveryNote> getAllDeliveryNotesForLogistics() {
        return deliveryNoteService.getDeliveryNotesForLogistics();
    }
    
    // Create new logistic department head
    @PostMapping("/create")
    public LogisticDepartment create(@RequestBody LogisticDepartment logisticDepartment) {
        return service.create(logisticDepartment);
    }

    // Get all logistic departments
    @GetMapping("/all")
    public List<LogisticDepartment> getAll() {
        return service.getAll();
    }

    // Get a specific department by ID
    @GetMapping("/{id}")
    public LogisticDepartment getById(@PathVariable String id) {
        return service.getById(id);
    }

    // Update phone, password, or status
    @PutMapping("/update/{id}")
    public LogisticDepartment update(@PathVariable String id, @RequestBody LogisticDepartment updated) {
        return service.update(id, updated);
    }
    
    @PostMapping("/login")
    public ResponseEntity<LogisticDepartment> login(@RequestBody LoginRequest request) {
        LogisticDepartment logistic = service.login(request.getId(), request.getPassword());
        return ResponseEntity.ok(logistic);
    }
}
