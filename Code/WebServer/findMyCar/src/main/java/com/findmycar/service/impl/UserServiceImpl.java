package com.findmycar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findmycar.dao.UserDao;
import com.findmycar.service.UserService;
import com.findmycar.to.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public boolean checkIfUserExists(User user) {

		return userDao.checkIfUserExists(user);
	}

	@Override
	public User createUser(User user) {

		return userDao.createUser(user);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUser(User user) {

		return userDao.findUserByIdOrEmailAddress(user);
	}

}
