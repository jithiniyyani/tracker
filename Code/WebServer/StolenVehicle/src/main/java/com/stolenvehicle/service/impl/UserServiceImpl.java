package com.stolenvehicle.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stolenvehicle.dao.UserDao;
import com.stolenvehicle.dto.PasswordTo;
import com.stolenvehicle.dto.SetPasswordTo;
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
		userTo.setActivationId(user.getActivation_id());
		return userTo;
	}

	@Override
	public boolean activateUser(String activationId) throws BusinessException {

		return userDao.activateUser(activationId);
	}

	@Override
	public String resetUserPassword(String userEmailId)
			throws BusinessException {
		return userDao.resetUserPassword(userEmailId);
	}

	@Override
	public boolean setUserPassword(SetPasswordTo setPasswordTo)
			throws BusinessException {
		return userDao.setUserPassword(setPasswordTo);
	}

	@Override
	public boolean updateUser(UserTo userTo) throws BusinessException {

		User user = ConversionUtil.covertUserTo(userTo);
		return userDao.updateUser(user);
	}

	@Override
	public boolean setPassword(PasswordTo passwordTo) throws BusinessException {

		return userDao.setPassword(passwordTo.getOldPassword(),
				passwordTo.getNewPassword(), passwordTo.getUserId());
	}
}
