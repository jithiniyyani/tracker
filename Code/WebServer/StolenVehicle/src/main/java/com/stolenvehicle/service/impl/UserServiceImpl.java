package com.stolenvehicle.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stolenvehicle.dao.UserDao;
import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.entity.User;
import com.stolenvehicle.exception.BusinessException;
import com.stolenvehicle.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public UserTo authenticateUser(UserTo user) throws BusinessException {

		User userFromDb = userDao.getUser(user.getEmailaddress(),
				user.getPassword());
		// copy from entity to dto
		return user;
	}

	@Override
	public UserTo registerUser(UserTo user) throws BusinessException {

		userDao.saveUser(null);
		// convert from entity to dto
		return user;
	}
}
