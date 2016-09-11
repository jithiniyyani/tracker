package com.stolenvehicle.service;

import com.stolenvehicle.dto.PasswordTo;
import com.stolenvehicle.dto.SetPasswordTo;
import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.exception.BusinessException;

public interface UserService {

	public UserTo authenticateUser(UserTo user) throws BusinessException;

	public UserTo registerUser(UserTo user) throws BusinessException;

	public boolean updateUser(UserTo user) throws BusinessException;

	public boolean activateUser(String activationId) throws BusinessException;

	public boolean setPassword(PasswordTo passwordTo) throws BusinessException;

	public String resetUserPassword(String userEmailId) throws BusinessException;

	public boolean setUserPassword(SetPasswordTo setPasswordTo) throws BusinessException;

}
