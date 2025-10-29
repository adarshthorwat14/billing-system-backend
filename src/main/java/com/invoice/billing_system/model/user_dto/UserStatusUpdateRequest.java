package com.invoice.billing_system.model.user_dto;

public class UserStatusUpdateRequest {
	
	 private String id;
	    private String userType; // client, distributor, productManager
	    private boolean status;
	    private String password;
	    
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUserType() {
			return userType;
		}
		public void setUserType(String userType) {
			this.userType = userType;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
  
	
}
