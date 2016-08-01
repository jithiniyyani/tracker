package com.stolenvehicle.service.impl;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stolenvehicle.dto.EmailTo;
import com.stolenvehicle.exception.BusinessException;
import com.stolenvehicle.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Value("#{properties['senderEmailId']}")
	private String senderEmailId;

	private static final Logger LOGGER = Logger.getLogger(EmailServiceImpl.class);

	@Resource(mappedName = "java:jboss/mail/Default")
	private Session mailSession;

	public String getSenderEmailId() {
		return senderEmailId;
	}

	public void setSenderEmailId(String senderEmailId) {
		this.senderEmailId = senderEmailId;
	}

	@Override
	public boolean sendEmail(EmailTo emailTo) throws BusinessException {

		LOGGER.error("Sending mail from " + this.senderEmailId + " to " + emailTo.getReceipent());
		LOGGER.error("Email message " + emailTo.getMessage());
		boolean status = false;
		/*try {
			MimeMessage m = new MimeMessage(mailSession);
			Address from = new InternetAddress(senderEmailId);
			Address[] to = new InternetAddress[] { new InternetAddress(emailTo.getReceipent()) };
			m.setFrom(from);
			m.setRecipients(Message.RecipientType.TO, to);
			m.setSubject(emailTo.getSubject());
			m.setSentDate(new java.util.Date());
			m.setContent(emailTo.getMessage(), "text/plain");
			Transport.send(m);
			status = true;
		} catch (javax.mail.MessagingException ex) {

			LOGGER.error("Error while sending email ", ex);

		}*/
		status = true;
		return status;
	}

}
