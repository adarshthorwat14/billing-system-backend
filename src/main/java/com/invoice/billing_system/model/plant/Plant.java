package com.invoice.billing_system.model.plant;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Plant {
	
	 @Id
	    private String plantId; // e.g., PLT001

	    private String name;    // e.g., Mumbai Plant
	    private String location;
	    private String description;
	    
	    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<PlantStock> plantStocks = new ArrayList<>();
	    
		public String getPlantId() {
			return plantId;
		}
		public void setPlantId(String plantId) {
			this.plantId = plantId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public List<PlantStock> getPlantStocks() {
			return plantStocks;
		}
		public void setPlantStocks(List<PlantStock> plantStocks) {
			this.plantStocks = plantStocks;
		}
	    
		
	    
}
