package com.example.demo.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;


import com.example.demo.domain.VehicleIdentificationNumber;
import com.example.demo.service.VehicleDetails;
import com.example.demo.service.VehicleIdentificationNumberNotFoundException;

/**
 * {@code @WebMvcTest} based tests for {@link UserVehicleController}.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserVehicleController.class)
public class UserVehicleControllerTest {

	private static final VehicleIdentificationNumber VIN = new VehicleIdentificationNumber(
			"01234567890123456");

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserVehicleService userVehicleService;
	
	
	@Test
	public void getVehicleWhenRequestingJsonShouldReturnMakeAndModel() throws Exception {
		given(this.userVehicleService.getVehicleDetails("donald"))
				.willReturn(new VehicleDetails("Honda", "Civic"));
		this.mvc.perform(get("/donald/vehicle").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("{'make':'Honda','model':'Civic'}"));
	}
	
	@Test
	public void getVehicleWhenUserNotFoundShouldReturnNotFound() throws Exception {
		given(this.userVehicleService.getVehicleDetails("donald"))
				.willThrow(new UserNotFoundException("donald"));
		this.mvc.perform(get("/donald/vehicle")).andExpect(status().isNotFound());
	}
	
	@Test
	public void getVehicleWhenVinNotFoundShouldReturnNotFound() throws Exception {
		given(this.userVehicleService.getVehicleDetails("donald"))
				.willThrow(new VehicleIdentificationNumberNotFoundException(VIN));
		this.mvc.perform(get("/donald/vehicle")).andExpect(status().isNotFound());
	}
	
}
