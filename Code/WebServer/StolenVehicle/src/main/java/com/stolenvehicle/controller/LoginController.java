package com.stolenvehicle.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stolenvehicle.constants.AuditEnum;
import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.ErrorEnum;
import com.stolenvehicle.constants.ExceptionConstants;
import com.stolenvehicle.constants.SuccessEnum;
import com.stolenvehicle.constants.UserStatusEnum;
import com.stolenvehicle.dto.EmailTo;
import com.stolenvehicle.dto.ErrorTo;
import com.stolenvehicle.dto.ResetPasswordTo;
import com.stolenvehicle.dto.SetPasswordTo;
import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.exception.ExceptionProcessor;
import com.stolenvehicle.service.AuditService;
import com.stolenvehicle.service.EmailService;
import com.stolenvehicle.service.TemplateService;
import com.stolenvehicle.service.UserService;
import com.stolenvehicle.util.AppUtil;
import com.stolenvehicle.util.JsonUtil;

@Controller
public class LoginController {

	private static final Logger LOGGER = Logger.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private TemplateService templateService;

	@Autowired
	private AuditService auditService;

	@RequestMapping(method = RequestMethod.POST, path = "/login")
	public ResponseEntity<String> login(@RequestBody String requestBody, HttpServletRequest request) {

		ResponseEntity<String> response;
		UserTo user = null;
		boolean status = false;
		String errorCause = null;
		try {

			user = JsonUtil.toObject(requestBody, Constants.USER, UserTo.class);
			user = userService.authenticateUser(user);
			HttpSession session = request.getSession(true);
			session.setAttribute(Constants.USER, user);
			response = new ResponseEntity<String>(
					JsonUtil.toJson(Constants.SUCCESS,
							new ErrorTo(SuccessEnum.LOGIN_SUCCESS.getCode(), SuccessEnum.LOGIN_SUCCESS.getMessage())),
					HttpStatus.OK);
			status = true;

		} catch (Exception ex) {

			errorCause = ex.toString();
			LOGGER.error("Error while authenticaing user json : " + requestBody, ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

			auditService.audit(user.getEmailaddress(), AuditEnum.LOGIN, status ? Constants.SUCCESS : Constants.ERROR,
					errorCause);
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/register")
	public ResponseEntity<String> register(@RequestBody String requestBody) {

		ResponseEntity<String> response;
		UserTo user = null;
		boolean status = false;
		String errorCause = null;
		try {

			// #TODO: handle trasaction here as well
			user = JsonUtil.toObject(requestBody, Constants.USER, UserTo.class);
			// #TODO: validate this json before passing it forward
			user.setUserStatus(UserStatusEnum.EMAIL_VERIFICATION_PENDING);
			user = userService.registerUser(user);
			String emailContent = templateService.generateContent(Constants.VM_REGISTRATION_EMAIL, Constants.USER, user,
					AppUtil.getResourceBundle(new Locale(Constants.US_LOCALE)));
			emailService.sendEmail(new EmailTo(user.getEmailaddress(), Constants.WELCOME_EMAIL_SUBJECT, emailContent));
			response = new ResponseEntity<String>(JsonUtil.toJson(Constants.SUCCESS,
					new ErrorTo(SuccessEnum.ACTIVATE_ACCOUNT.getCode(), SuccessEnum.ACTIVATE_ACCOUNT.getMessage())),
					HttpStatus.OK);
			status = true;

		} catch (Exception ex) {

			errorCause = ex.toString();
			LOGGER.error("Error while registering user request " + requestBody, ex);
			response = ExceptionProcessor.handleException(ex);

		} finally {

			auditService.audit(user.getEmailaddress(), AuditEnum.REGISTER, status ? Constants.SUCCESS : Constants.ERROR,
					errorCause);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {
		ResponseEntity<String> response = null;
		boolean invalidateSession = AppUtil.invalidateSession(request);
		response = invalidateSession ? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		// to be improved upon
		auditService.audit(Constants.NO_USER_ID, AuditEnum.LOGOUT, Constants.SUCCESS, null);
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/activateUser")
	public ResponseEntity<String> activate(@RequestParam(value = "id") String activationId,
			HttpServletRequest request) {
		ResponseEntity<String> response = null;
		String errorCause = "Invalid activation id";
		boolean activateUser = false;
		try {

			if (StringUtils.isEmpty(activationId)) {
				throw new IllegalArgumentException(ExceptionConstants.EMPTY_INPUT);
			} else {

				activateUser = userService.activateUser(activationId);
				response = activateUser ? new ResponseEntity<String>(HttpStatus.OK)
						: new ResponseEntity<String>(
								JsonUtil.toJson(Constants.ERROR,
										new ErrorTo(ErrorEnum.ACTIVATE_USER_ID_NOT_FOUND.getCode(),
												ErrorEnum.ACTIVATE_USER_ID_NOT_FOUND.getMessage())),
								HttpStatus.BAD_REQUEST);

			}
		} catch (Exception ex) {

			errorCause = ex.toString();
			LOGGER.error("Error while activating user activation id " + activationId, ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

			auditService.audit(activationId != null ? activationId : "NOT_VALID", AuditEnum.ACTIVATE_USER,
					activateUser ? Constants.SUCCESS : Constants.ERROR, errorCause);
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/restPassword")
	public ResponseEntity<String> resetPassword(@RequestBody String jsonRequest, HttpServletRequest request) {
		ResponseEntity<String> response = null;
		ResetPasswordTo passwordResetTo = null;
		boolean status = false;
		String errorCause = "";
		try {

			passwordResetTo = JsonUtil.toObject(jsonRequest, Constants.RESET_PASSWORD, ResetPasswordTo.class);
			String activationCode = userService.resetUserPassword(passwordResetTo.getEmailAddress());
			passwordResetTo.setActivationCode(activationCode);

			String emailContent = templateService.generateContent(Constants.VM_RESET_EMAIL, Constants.RESET_PASSWORD,
					passwordResetTo, AppUtil.getResourceBundle(new Locale(Constants.US_LOCALE)));

			emailService.sendEmail(
					new EmailTo(passwordResetTo.getEmailAddress(), Constants.PASSWORD_EMAIL_SUBJECT, emailContent));
			response = new ResponseEntity<String>(HttpStatus.OK);
			status = true;

		} catch (Exception ex) {

			LOGGER.error("Error while reseting password request : " + request, ex);
			errorCause = ex.getMessage();
			response = ExceptionProcessor.handleException(ex);
		} finally {

			auditService.audit(passwordResetTo.getEmailAddress(), AuditEnum.RESET_PASSWORD,
					status ? Constants.SUCCESS : Constants.ERROR, errorCause);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/setPassword")
	public ResponseEntity<String> setPassword(@RequestBody String jsonRequest, HttpServletRequest request) {
		ResponseEntity<String> response = null;
		boolean status = false;
		SetPasswordTo setPasswordTo = null;
		String errorCause = "";
		try {

			setPasswordTo = JsonUtil.toObject(jsonRequest, Constants.SET_PASSWORD, SetPasswordTo.class);
			status = userService.setUserPassword(setPasswordTo);
			response = status ? new ResponseEntity<String>(HttpStatus.OK)
					: new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

		} catch (Exception ex) {

			LOGGER.error("Error while setting password request : " + jsonRequest, ex);
			errorCause = ex.getMessage();
			response = ExceptionProcessor.handleException(ex);
		} finally {

			auditService.audit(setPasswordTo.getEmailAddress(), AuditEnum.SET_PASSWORD,
					status ? Constants.SUCCESS : Constants.ERROR, errorCause);
		}
		return response;
	}
}
