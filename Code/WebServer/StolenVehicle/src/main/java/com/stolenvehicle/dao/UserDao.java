package com.stolenvehicle.dao;

import com.stolenvehicle.entity.User;
import com.stolenvehicle.exception.BusinessException;

public interface UserDao extends GenericDao {

	public User getUser(String emailAddress, String password) throws BusinessException;

}
