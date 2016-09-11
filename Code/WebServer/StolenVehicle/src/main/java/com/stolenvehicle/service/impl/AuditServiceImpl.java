package com.stolenvehicle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.AuditEnum;
import com.stolenvehicle.dao.AuditDao;
import com.stolenvehicle.service.AuditService;

@Service
public class AuditServiceImpl implements AuditService {

	@Autowired
	private AuditDao auditDao;

	@Override
	@Async
	public void audit(String userId, AuditEnum operation, String status, String errorCause) {

		auditDao.audit(userId, operation, status, errorCause);

	}

}
