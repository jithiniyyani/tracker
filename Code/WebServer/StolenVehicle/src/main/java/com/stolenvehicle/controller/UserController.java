package com.stolenvehicle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stolenvehicle.constants.AuditEnum;
import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.ExceptionConstants;
import com.stolenvehicle.dto.PasswordTo;
import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.exception.BusinessException;
import com.stolenvehicle.exception.ExceptionProcessor;
import com.stolenvehicle.service.AuditService;
import com.stolenvehicle.service.UserService;
import com.stolenvehicle.util.AppUtil;
import com.stolenvehicle.util.JsonUtil;

@Controller
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private AuditService auditService;

	@RequestMapping(method = RequestMethod.GET, path = "/ping")
	public ResponseEntity<String> ping() {
		return new ResponseEntity<String>("Test ", HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public ResponseEntity<String> getUserDetails(HttpServletRequest request) {

		ResponseEntity<String> response = null;
		try {

			AppUtil.checkIfUserHasSession(request);
			UserTo user = AppUtil.getUserFromSession(request);
			response = new ResponseEntity<String>(JsonUtil.toJson(Constants.USER, user), HttpStatus.OK);
		} catch (Exception ex) {

			LOGGER.debug("Error while getting user ", ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public ResponseEntity<String> updateUserProfile(@RequestBody String jsonRequest, HttpServletRequest request) {

		ResponseEntity<String> response = null;
		UserTo user = null;
		boolean status = false;
		String errorCause = "";
		try {
			AppUtil.checkIfUserHasSession(request);
			user = JsonUtil.toObject(jsonRequest, Constants.USER, UserTo.class);
			userService.updateUser(user);
			status = true;
			HttpSession session = request.getSession();
			session.setAttribute(Constants.USER, user);
			response = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {

			LOGGER.error("Error while getting user ", ex);
			errorCause = ex.getMessage();
			response = ExceptionProcessor.handleException(ex);
		} finally {

			auditService.audit(user.getEmailaddress(), AuditEnum.UPDATE_PROFILE,
					status ? Constants.SUCCESS : Constants.ERROR, errorCause);
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/password")
	public ResponseEntity<String> updatePassword(@RequestBody String jsonRequest, HttpServletRequest request) {

		ResponseEntity<String> response = null;
		UserTo user = null;
		boolean status = false;
		String errorCause = "";
		try {
			AppUtil.checkIfUserHasSession(request);
			user = AppUtil.getUserFromSession(request);
			PasswordTo passwordTo = JsonUtil.toObject(jsonRequest, Constants.PASSWORD, PasswordTo.class);
			if (!passwordTo.getOldPassword().equals(user.getPassword())) {
				throw new BusinessException(ExceptionConstants.OLD_PASSWORD_NOT_VALID);
			}
			status = userService.setPassword(passwordTo);
			if (status) {
				AppUtil.invalidateSession(request);
			}
			response = status ? new ResponseEntity<String>(HttpStatus.OK)
					: new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {

			LOGGER.error("Error while getting user ", ex);
			errorCause = ex.getMessage();
			response = ExceptionProcessor.handleException(ex);
		} finally {

			auditService.audit(user.getEmailaddress(), AuditEnum.CHANGE_PASSWORD,
					status ? Constants.SUCCESS : Constants.ERROR, errorCause);
		}
		return response;
	}
}
