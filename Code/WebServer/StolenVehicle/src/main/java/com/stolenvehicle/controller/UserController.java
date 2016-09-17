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

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.dto.PasswordTo;
import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.exception.ExceptionProcessor;
import com.stolenvehicle.service.UserService;
import com.stolenvehicle.util.AppUtil;
import com.stolenvehicle.util.JsonUtil;

@Controller
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

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
			response = new ResponseEntity<String>(JsonUtil.toJson(
					Constants.USER, user), HttpStatus.OK);
		} catch (Exception ex) {

			LOGGER.error("Error while getting user ", ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public ResponseEntity<String> updateUserProfile(
			@RequestBody String jsonRequest, HttpServletRequest request) {

		ResponseEntity<String> response = null;
		try {
			AppUtil.checkIfUserHasSession(request);
			UserTo user = JsonUtil.toObject(jsonRequest, Constants.USER,
					UserTo.class);
			userService.updateUser(user);
			HttpSession session = request.getSession();
			session.setAttribute(Constants.USER, user);

			response = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {

			LOGGER.error("Error while getting user ", ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/password")
	public ResponseEntity<String> updatePassword(
			@RequestBody String jsonRequest, HttpServletRequest request) {

		ResponseEntity<String> response = null;
		try {
			PasswordTo passwordTo = JsonUtil.toObject(jsonRequest,
					Constants.PASSWORD, PasswordTo.class);
			userService.setPassword(passwordTo);
			response = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {

			LOGGER.error("Error while getting user ", ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

		}
		return response;
	}
}
