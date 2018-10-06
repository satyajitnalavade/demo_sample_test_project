package com.example.demo.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.VehicleDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * {@code @SpringBootTest} based tests for {@link UserVehicleController}.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserVehicleControllerApplicationTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserVehicleService userVehicleService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void getVehicleWhenRequestingTextShouldReturnMakeAndModel() throws Exception {
		given(this.userVehicleService.getVehicleDetails("donald"))
				.willReturn(new VehicleDetails("Honda", "Civic"));
		
		String vehicleDetailsString = 
				objectMapper.writeValueAsString(new VehicleDetails("Honda", "Civic"));
		this.mvc.perform(get("/donald/vehicle").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(vehicleDetailsString) );
	}

}
