package com.example.demo.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.VehicleDetails;
import com.example.demo.service.VehicleIdentificationNumberNotFoundException;

@RestController
public class UserVehicleController {

	private UserVehicleService userVehicleService;

	public UserVehicleController(UserVehicleService userVehicleService) {

		this.userVehicleService = userVehicleService;
	}

	@GetMapping(path = "/", produces = MediaType.ALL_VALUE)
	public String sayHello() {
		return "Hello";
	}

	@GetMapping(path = "/{username}/vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
	public VehicleDetails UserVehicleDetailsJson(@PathVariable String username) {
		return this.userVehicleService.getVehicleDetails(username);
	}

	@GetMapping(path = "/vehicle/{vin}/details", produces = MediaType.APPLICATION_JSON_VALUE)
	public VehicleDetails VehicleDetailsJson(@PathVariable String vin) {

		return new VehicleDetails("honda", "accord");

	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void handleVinNotFound(VehicleIdentificationNumberNotFoundException ex) {
	}

}
