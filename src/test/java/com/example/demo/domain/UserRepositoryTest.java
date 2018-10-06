package com.example.demo.domain;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tests for {@link UserRepository}.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	private static final VehicleIdentificationNumber VIN = new VehicleIdentificationNumber(
			"01234567890123456");

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository repository;

	@Test
	public void findByUsernameShouldReturnUser() throws Exception {
		this.entityManager.persist(new User("donald", VIN));
		User user = this.repository.findByUserName("donald");
		assertThat(user.getUserName()).isEqualTo("donald");
		assertThat(user.getVin()).isEqualTo(VIN);
	}
	
	@Test
	public void findByUsernameWhenNoUserShouldReturnNull() throws Exception {
		this.entityManager.persist(new User("donald", VIN));
		User user = this.repository.findByUserName("minnie");
		assertThat(user).isNull();
	}
	
	
}
