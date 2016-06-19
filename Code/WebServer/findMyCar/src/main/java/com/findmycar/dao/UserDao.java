package com.findmycar.dao;

import com.findmycar.to.User;

public interface UserDao extends GenericDao {

	public User createUser(User user);

	public User updateUser();

	public User findUserById();

	public User findUserByIdOrEmailAddress(User user);

	public boolean checkIfUserExists(User user);
}