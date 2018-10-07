package com.example.demo.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("vehicle.service")
@Data
public class RemoteVehicleDetailsServiceProperties {

	
	public String getRootUrl() {
		return rootUrl;
	}

	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}

	private String rootUrl = "http://localhost:8080/";
	
	
	
	
}