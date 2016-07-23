package com.stolenvehicle.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.exception.BusinessException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/context.xml")
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void validAuthTest() throws Exception {

		UserTo user = new UserTo();
		user.setEmailaddress("roger@gmail.com");
		user.setPassword("roger");
		userService.authenticateUser(user);
	}

	@Test(expected = BusinessException.class)
	public void inValidAuthTest() throws Exception {

		UserTo user = new UserTo();
		user.setEmailaddress("roger@gmail.com");
		user.setPassword("roger1");
		userService.authenticateUser(user);
	}
}
