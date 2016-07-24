package com.stolenvehicle.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stolenvehicle.dao.UserDao;
import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.entity.User;
import com.stolenvehicle.exception.BusinessException;
import com.stolenvehicle.service.UserService;
import com.stolenvehicle.util.ConversionUtil;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public UserTo authenticateUser(UserTo userTo) throws BusinessException {

		User userFromDb = userDao.getUser(userTo.getEmailaddress(),
				userTo.getPassword());
		userTo = ConversionUtil.convertUserEntity(userFromDb);
		return userTo;
	}

	@Override
	public UserTo registerUser(UserTo userTo) throws BusinessException {

		User user = ConversionUtil.covertUserTo(userTo);
		user = userDao.saveUser(user);
		userTo.setId(user.getId());
		return userTo;
	}
}
