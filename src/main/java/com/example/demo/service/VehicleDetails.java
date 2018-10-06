package com.example.demo.service;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleDetails {

	private String make;
	private String model;
	
	@JsonCreator
	public VehicleDetails(@JsonProperty("make") String make, @JsonProperty("model") String model) {
		Assert.notNull(make, "Make should not be null");
		Assert.notNull(model, "Model should not be null");
		this.make = make;
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	
	
	
}
