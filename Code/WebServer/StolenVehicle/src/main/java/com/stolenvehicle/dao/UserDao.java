package com.stolenvehicle.dao;

import com.stolenvehicle.dto.SetPasswordTo;
import com.stolenvehicle.entity.User;
import com.stolenvehicle.exception.BusinessException;

public interface UserDao extends GenericDao {

	public User getUser(String emailAddress, String password)
			throws BusinessException;

	public User saveUser(User user) throws BusinessException;

	public boolean activateUser(String activationId) throws BusinessException;

	public String resetUserPassword(String emailId) throws BusinessException;

	public boolean setUserPassword(SetPasswordTo setPasswordTo)
			throws BusinessException;

	public String getEmailByUserId(String user_id) throws BusinessException;
}
