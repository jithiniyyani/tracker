package com.stolenvehicle.service;

import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.exception.BusinessException;

public interface UserService {

	public UserTo authenticateUser(UserTo user) throws BusinessException;

	public UserTo registerUser(UserTo user) throws BusinessException;
}
