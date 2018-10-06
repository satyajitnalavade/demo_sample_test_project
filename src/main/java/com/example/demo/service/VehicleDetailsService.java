package com.example.demo.service;

import com.example.demo.domain.VehicleIdentificationNumber;

public interface VehicleDetailsService {
	
	VehicleDetails getVehicleDetails(VehicleIdentificationNumber vin);

}
