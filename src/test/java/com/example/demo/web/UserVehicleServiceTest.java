package com.example.demo.web;

import static org.mockito.Matchers.anyString;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.domain.UserRepository;
import com.example.demo.domain.VehicleIdentificationNumber;
import com.example.demo.service.RemoteVehicleDetailsService;
import com.example.demo.service.VehicleDetailsService;
import static org.mockito.BDDMockito.given;

/**
 * Tests for {@link UserVehicleService}.
 */

public class UserVehicleServiceTest {
	
	private static final VehicleIdentificationNumber VIN = new VehicleIdentificationNumber(
			"01234567890123456");

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Mock
	private VehicleDetailsService vehicleDetailsService;

	@Mock
	private UserRepository userRepository;
	
	private UserVehicleService service;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.service = new UserVehicleService(this.userRepository,
				this.vehicleDetailsService);
	}
	
	@Test
	public void getVehicleDetailsWhenUsernameIsNullShouldThrowException()
			throws Exception {
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Username should not be null");
		this.service.getVehicleDetails(null);
	}
	
	@Test
	public void getVehicleDetailsWhenUsernameNotFoundShouldThrowException()
			throws Exception {
		given(this.userRepository.findByUserName(anyString())).willReturn(null);
		this.thrown.expect(UserNotFoundException.class);
		this.service.getVehicleDetails("donald");
	}
	
	
	
}
