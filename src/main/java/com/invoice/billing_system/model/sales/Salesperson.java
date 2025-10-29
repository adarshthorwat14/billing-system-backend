package com.invoice.billing_system.model.sales;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "salespersons")

public class Salesperson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salesperson_seq")
    @SequenceGenerator(
        name = "salesperson_seq",
        sequenceName = "salesperson_sequence",
        initialValue = 4000,
        allocationSize = 1
    )
    private Long id;

    private String name;
    private String phoneNumber;
    private String password;
    private String email;
    private String position;
    private String state;
    private String region;
    private String territory;
    private Double target;
    private Double achieved;
    private String status;
    private String branch;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getTerritory() {
		return territory;
	}
	public void setTerritory(String territory) {
		this.territory = territory;
	}
	public Double getTarget() {
		return target;
	}
	public void setTarget(Double target) {
		this.target = target;
	}
	public Double getAchieved() {
		return achieved;
	}
	public void setAchieved(Double achieved) {
		this.achieved = achieved;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Salesperson(Long id, String name, String phoneNumber,String password,  String email, String position, String state,
			String region, String branch, String territory, Double target, Double achieved, String status) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.email = email;
		this.position = position;
		this.state = state;
		this.region = region;
		this.branch = branch;
		this.territory = territory;
		this.target = target;
		this.achieved = achieved;
		this.status = status;
	}
	public Salesperson() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    
    
}

