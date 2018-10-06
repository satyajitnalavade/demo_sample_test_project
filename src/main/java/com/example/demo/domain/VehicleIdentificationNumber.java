package com.example.demo.domain;

import org.springframework.util.Assert;

import lombok.Data;

@Data
public class VehicleIdentificationNumber {
	
	private String vin;
	
	public VehicleIdentificationNumber(String vin) {
		Assert.notNull(vin, " Vin must not be Null");
		Assert.isTrue(vin.length() ==  17, "Vin must be exactly 17 characters");
		this.vin = vin;
	}

	@Override
	public int hashCode() {
		return this.vin.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != getClass()) {
			return false;
		}
		return this.vin.equals(((VehicleIdentificationNumber) obj).vin);
	}

	@Override
	public String toString() {
		return this.vin;
	}

}
