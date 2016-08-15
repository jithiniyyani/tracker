package com.stolenvehicle.dao;

import com.stolenvehicle.constants.AuditEnum;

public interface AuditDao extends GenericDao {

	public void audit(String userId, AuditEnum operation, String status,
			String errorCause);

}
