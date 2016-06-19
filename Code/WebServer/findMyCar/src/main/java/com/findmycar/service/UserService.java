package com.findmycar.service;

import com.findmycar.to.User;

public interface UserService {

	public boolean checkIfUserExists(User user);

	public User createUser(User user);

	public User updateUser(User user);

	public User findUser(User user);
	
}
