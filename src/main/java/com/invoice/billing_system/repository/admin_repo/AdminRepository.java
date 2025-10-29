package com.invoice.billing_system.repository.admin_repo;



import com.invoice.billing_system.model.admin.Admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Long> {
	Optional<Admin> findByEmail(String email);
}

