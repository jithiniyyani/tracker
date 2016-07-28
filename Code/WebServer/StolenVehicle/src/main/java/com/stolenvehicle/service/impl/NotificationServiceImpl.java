package com.stolenvehicle.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.dao.UserDao;
import com.stolenvehicle.dto.EmailTo;
import com.stolenvehicle.exception.BusinessException;
import com.stolenvehicle.service.EmailService;
import com.stolenvehicle.service.NotificationService;
import com.stolenvehicle.service.TemplateService;
import com.stolenvehicle.util.AppUtil;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private TemplateService templateService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserDao userDao;

	@Override
	public boolean sendFindNotification(String user_id)
			throws BusinessException {

		boolean status = false;
		String emailByUserId = userDao.getEmailByUserId(user_id);
		String emailContent = templateService.generateContent(
				Constants.VM_NOTIFICATOIN_EMAIL, Constants.USER, emailByUserId,
				AppUtil.getResourceBundle(new Locale(Constants.US_LOCALE)));
		emailService.sendEmail(new EmailTo(emailByUserId,
				Constants.NOTIFICATION_EMAIL_SUBJECT, emailContent));
		status = true;
		return status;
	}

}
