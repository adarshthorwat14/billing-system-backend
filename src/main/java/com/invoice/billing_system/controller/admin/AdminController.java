package com.invoice.billing_system.controller.admin;


import com.invoice.billing_system.model.admin_dto.AdminRequest;
import com.invoice.billing_system.model.admin_dto.LoginRequest;
import com.invoice.billing_system.repository.admin_repo.AdminRepository;
import com.invoice.billing_system.model.admin.Admin;
import com.invoice.billing_system.service.admin_serv.AdminService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/admins")			
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    private final AdminRepository adminRepository;
    
    @Autowired
    public AdminController(AdminRepository adminRepository) {
    	this.adminRepository = adminRepository;
    }

    @PostMapping
    public Admin createAdmin(@RequestBody AdminRequest request) {
        return adminService.createAdmin(request);
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getAdminCount() {
        long count = adminRepository.count(); // ✅ call on the instance, not statically
        Map<String, Long> response = Collections.singletonMap("count", count);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> login(@RequestBody LoginRequest request) {
        Admin admin = adminService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(admin);
    }
    
//    @PutMapping("/{id}")
//    public Admin updateAdmin(@PathVariable Long id, @RequestBody AdminRequest request) {
//        return adminService.updateAdmin(id, request);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteAdmin(@PathVariable Long id) {
//        adminService.deleteAdmin(id);
//    }
//
//    @GetMapping("/{id}")
//    public Admin getAdminById(@PathVariable Long id) {
//        return adminService.getAdminById(id);
//    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }
}

