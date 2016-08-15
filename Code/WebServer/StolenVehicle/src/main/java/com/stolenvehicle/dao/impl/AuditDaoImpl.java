package com.stolenvehicle.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.AuditEnum;
import com.stolenvehicle.constants.Query;
import com.stolenvehicle.dao.AuditDao;
import com.stolenvehicle.exception.BusinessException;

@Service
public class AuditDaoImpl extends AbstractDao implements AuditDao {

	private static final Logger LOGGER = Logger.getLogger(AuditDaoImpl.class);

	@Override
	public void audit(String userId, AuditEnum operation, String status,
			String errorCause) {

		try {
			this.save(
					Query.AUDIT_INSERT,
					new Object[] {
							userId,
							operation.toString(),
							status,
							errorCause != null ? errorCause.substring(0,
									(errorCause.length() > 255 ? 255
											: errorCause.length())) : null });
		} catch (BusinessException e) {

			LOGGER.error("Error while saving auidt " + e);
		}

	}
}
