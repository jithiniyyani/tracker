package com.stolenvehicle.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stolenvehicle.entity.User;
import com.stolenvehicle.exception.BusinessException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/context.xml")
public class UserDaoImplTest {

	@Autowired
	private UserDao userDao;

	@Test(expected = BusinessException.class)
	public void invalidUserNameOrPassword() throws Exception {

		userDao.getUser("roger@gmail.com", "test1");

	}

	@Test
	public void validUserNameAndPassword() throws Exception {

		User user = userDao.getUser("roger@gmail.com", "roger");
		if (user != null) {

		} else {

		}

	}

}
