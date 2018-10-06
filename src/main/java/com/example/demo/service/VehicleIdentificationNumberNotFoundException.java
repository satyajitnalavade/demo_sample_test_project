package com.example.demo.service;

import com.example.demo.domain.VehicleIdentificationNumber;

public class VehicleIdentificationNumberNotFoundException extends RuntimeException {
	
	private VehicleIdentificationNumber vehicleIdentificationNumber;
	
	public VehicleIdentificationNumberNotFoundException(VehicleIdentificationNumber vin) {
		this(vin, null);
	}

	public VehicleIdentificationNumberNotFoundException(VehicleIdentificationNumber vin,
			Throwable cause) {
		super("VehicleIdentificationNumber not found  " + vin, cause);
		this.vehicleIdentificationNumber = vin;
	}

}
