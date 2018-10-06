package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON tests for {@link VehicleDetails}.
 */

@RunWith(SpringRunner.class)
@JsonTest
public class VehicleDetailsJsonTest {

	@Autowired
	private JacksonTester<VehicleDetails> json;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void serializeJson() throws Exception {
		VehicleDetails details = new VehicleDetails("Honda", "Civic");
		String vehicleDetailsString = objectMapper.writeValueAsString(new VehicleDetails("Honda", "Civic"));
		assertThat(this.json.write(details)).isEqualToJson(vehicleDetailsString);
		assertThat(this.json.write(details)).hasJsonPathStringValue("@.make");
		assertThat(this.json.write(details)).extractingJsonPathStringValue("@.make")
				.isEqualTo("Honda");
	}
	
	@Test
	public void deserializeJson() throws Exception {
		String content = "{\"make\":\"Ford\",\"model\":\"Focus\"}";
		assertThat(this.json.parse(content)).isEqualToComparingFieldByField(new VehicleDetails("Ford", "Focus"));
		assertThat(this.json.parseObject(content).getMake()).isEqualTo("Ford");
	}
	
}
