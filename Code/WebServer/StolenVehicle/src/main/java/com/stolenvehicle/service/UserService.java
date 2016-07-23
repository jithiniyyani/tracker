package com.stolenvehicle.service;

import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.exception.BusinessException;

public interface UserService {

	public boolean authenticateUser(UserTo user) throws BusinessException;
}
