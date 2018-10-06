package com.example.demo.web;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import com.example.demo.service.RemoteVehicleDetailsService;
import com.example.demo.service.VehicleDetails;
import com.example.demo.service.VehicleDetailsService;

@Component
public class UserVehicleService {

	private final UserRepository userRepository;
	private final VehicleDetailsService vehicleDetailsService;
	
	public UserVehicleService(UserRepository userRepository, VehicleDetailsService vehicleDetailsService) {
		this.userRepository = userRepository;
		this.vehicleDetailsService = vehicleDetailsService;
	}
	
	public VehicleDetails getVehicleDetails(String username) {
		Assert.notNull(username,"Username should not be null");
		User user = userRepository.findByUserName(username);
		if (user==null) {
			throw new UserNotFoundException(username);
		}
		
		return this.vehicleDetailsService.getVehicleDetails(user.getVin());
	}
	
	
}