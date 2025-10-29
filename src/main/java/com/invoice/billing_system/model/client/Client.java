package com.invoice.billing_system.model.client;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Client {

    @Id
    private String id;

    private String name;
    private String email;
    private String phone;
    private String address;

    private String city;
    private String district;
    private String state;
    private String pancard;
    private String gst;
    
    private List<String> productDeal;
    @ElementCollection
    @CollectionTable(name = "client_distributor_ids", joinColumns = @JoinColumn(name = "client_id"))
    @Column(name = "distributor_id")
    private List<String> distributorId;
    private boolean status;
    
    
    @Column(length = 12)
    private String adharCard;

    @Column(length = 6)
    private String pincode;
    
    private String password;
    // Constructors
    public Client() {
        // default active status
        this.status = true; 
    }

    public Client(String id, String name, String email, String phone, String address,
                  String city, String district, String state, String pancard,
                  String gst, String adharCard, String pincode, String password,List<String> productDeal, boolean status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.district = district;
        this.state = state;
        this.pancard = pancard;
        this.gst = gst;
        this.adharCard = adharCard;
        this.pincode = pincode;
        this.password = password;
        this.status = status;
        this.productDeal = productDeal;
    }

    // Getters and Setters

    public List<String> getProductDeal() {
		return productDeal;
	}

	public void setProductDeal(List<String> productDeal) {
		this.productDeal = productDeal;
	}

	
	public List<String> getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(List<String> distributorId) {
		this.distributorId = distributorId;
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

    public String getPhone() {
        return phone;
    }

    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPancard() {
        return pancard;
    }

    public void setPancard(String pancard) {
        this.pancard = pancard;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getAdharCard() {
        return adharCard;
    }

    public void setAdharCard(String adharCard) {
        this.adharCard = adharCard;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean active) {
        this.status = active;
    }
}
