package com.invoice.billing_system.model.product_mng;



import jakarta.persistence.*;

@Entity
@Table(name = "productMng")
public class ProductManagement {

	@Id
    private String id;
	private String name;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private String role;
    
    private boolean status;
    
    public ProductManagement() {
		// TODO Auto-generated constructor stub
    	this.status = true; 
	}

	public ProductManagement(String id, String name, String email, String username, String password,
			String phoneNumber, String role,boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.status = status;
	}
    
	   public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
	
}
