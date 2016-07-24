package com.stolenvehicle.service;

import com.stolenvehicle.dto.EmailTo;
import com.stolenvehicle.exception.BusinessException;

public interface EmailService {

	public boolean sendEmail(EmailTo emailTo) throws BusinessException;
}
