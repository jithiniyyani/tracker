package com.stolenvehicle.service;

import com.stolenvehicle.exception.BusinessException;

public interface NotificationService {

	public boolean sendFindNotification(String user_id) throws BusinessException;
}
