package com.stolenvehicle.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stolenvehicle.dao.UserDao;
import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.exception.BusinessException;
import com.stolenvehicle.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public boolean authenticateUser(UserTo user) throws BusinessException {

		boolean status = false;
		userDao.getUser(user.getEmailaddress(), user.getPassword());
		status = true;
		return status;
	}
}
