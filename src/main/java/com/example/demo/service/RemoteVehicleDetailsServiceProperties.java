package com.example.demo.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("vehicle.service")
@Data
public class RemoteVehicleDetailsServiceProperties {

	
	private String rootUrl = "http://localhost:8080/";
	
	
	
	
}