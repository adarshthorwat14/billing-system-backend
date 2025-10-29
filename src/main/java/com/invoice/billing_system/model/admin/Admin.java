package com.invoice.billing_system.model.admin;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

	 @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")
	    @SequenceGenerator(
	        name = "admin_seq",
	        sequenceName = "admin_id_seq", // match with your DB sequence name
	        allocationSize = 1,
	        initialValue = 8000
	    )
    private Long adminId;

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;

    // Constructors
    public Admin() {}

    public Admin(String name, String email, String password, String phoneNumber, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    // Getters & Setters
    public Long getAdminId() {
        return adminId;
    }

    public void setId(Long adminId) {
    	
    	this.adminId = adminId;
    	}

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getRole() {
        return role;
    }

    public void setRole(String role) { this.role = role; }
}
