package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.util.Assert;

@Entity
public class User {
	


	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true)
	private String userName;
	
	private VehicleIdentificationNumber vin;
	
	//for hibernate you will need default constructor but make it private
	protected User() {
	}
	
	public User( String userName, VehicleIdentificationNumber vin) {
		Assert.hasLength(userName, "userName should not be empty");
		Assert.notNull(vin, "vin should not be null");
		this.userName = userName;
		this.vin = vin;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public VehicleIdentificationNumber getVin() {
		return vin;
	}

	public void setVin(VehicleIdentificationNumber vin) {
		this.vin = vin;
	}
	
}