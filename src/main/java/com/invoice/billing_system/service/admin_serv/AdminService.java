package com.invoice.billing_system.service.admin_serv;



import com.invoice.billing_system.model.admin.Admin;
import com.invoice.billing_system.model.admin_dto.AdminRequest;
import com.invoice.billing_system.repository.admin_repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    
    

    public Admin createAdmin(AdminRequest request) {
        Admin admin = new Admin(
                request.getName(),
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNumber(),
                request.getRole()
        );
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, AdminRequest request) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));

        admin.setName(request.getName());
        admin.setEmail(request.getEmail());
        admin.setPassword(request.getPassword());
        admin.setPhoneNumber(request.getPhoneNumber());
        admin.setRole(request.getRole());

        return adminRepository.save(admin);
    }
    
    public Admin login(String email, String password) {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin not found with email: " + email));

        if (!admin.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return admin;
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}

