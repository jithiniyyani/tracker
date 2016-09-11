package com.stolenvehicle.service;

import com.stolenvehicle.constants.AuditEnum;

public interface AuditService {

	public void audit(String userId, AuditEnum operation, String status, String errorCause);

}
