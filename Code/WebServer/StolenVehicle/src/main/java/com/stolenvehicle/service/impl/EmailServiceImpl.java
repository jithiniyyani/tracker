package com.stolenvehicle.service.impl;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
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

	@Value("#{properties['smtphost']}")
	private String smtphost;

	@Value("#{properties['smtpport']}")
	private String smtpport;

	@Value("#{properties['senderEmailId']}")
	private String senderEmailId;

	private Properties properties;

	private static final Logger LOGGER = Logger
			.getLogger(EmailServiceImpl.class);

	@PostConstruct
	public void init() {

		properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", smtphost);
		properties.put("mail.smtp.port", smtpport);
	}

	@Override
	public boolean sendEmail(EmailTo emailTo) throws BusinessException {

		boolean status = false;
		final String password = "ourpassword";

		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(senderEmailId,
								password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmailId));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailTo.getReceipent()));
			message.setSubject(emailTo.getSubject());
			message.setText(emailTo.getMessage());

			Transport.send(message);
			status = true;

		} catch (Exception ex) {

			LOGGER.error("Error while sending email", ex);
		}

		return status;

	}

	public String getSmtphost() {
		return smtphost;
	}

	public void setSmtphost(String smtphost) {
		this.smtphost = smtphost;
	}

	public String getSmtpport() {
		return smtpport;
	}

	public void setSmtpport(String smtpport) {
		this.smtpport = smtpport;
	}

	public String getSenderEmailId() {
		return senderEmailId;
	}

	public void setSenderEmailId(String senderEmailId) {
		this.senderEmailId = senderEmailId;
	}

}
