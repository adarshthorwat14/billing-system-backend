package com.invoice.billing_system.model.vehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicle_requests_from_plant")
public class VehicleRequestPlant {
	
		 @Id
	    private String transportId;

	    private String requestId;
	    private String vehicleNumber;
	    private String driverName;
	    private String fromLocation;
	    private String toLocation;
	    private double km;
	    private double perKmRate;
	    private double totalAmount;
	    private String status;
	    
		public String getTransportId() {
			return transportId;
		}
		public void setTransportId(String transportId) {
			this.transportId = transportId;
		}
		public String getRequestId() {
			return requestId;
		}
		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}
		public String getVehicleNumber() {
			return vehicleNumber;
		}
		public void setVehicleNumber(String vehicleNumber) {
			this.vehicleNumber = vehicleNumber;
		}
		
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDriverName() {
			return driverName;
		}
		public void setDriverName(String driverName) {
			this.driverName = driverName;
		}
		public String getFromLocation() {
			return fromLocation;
		}
		public void setFromLocation(String fromLocation) {
			this.fromLocation = fromLocation;
		}
		public String getToLocation() {
			return toLocation;
		}
		public void setToLocation(String toLocation) {
			this.toLocation = toLocation;
		}
		public double getKm() {
			return km;
		}
		public void setKm(double km) {
			this.km = km;
		}
		public double getPerKmRate() {
			return perKmRate;
		}
		public void setPerKmRate(double perKmRate) {
			this.perKmRate = perKmRate;
		}
		public double getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}
	    
	    
	    
}
