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
import com.stolenvehicle.constants.ErrorEnum;
import com.stolenvehicle.constants.ExceptionConstants;
import com.stolenvehicle.dto.ErrorTo;
import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.exception.BusinessException;
import com.stolenvehicle.service.UserService;
import com.stolenvehicle.util.AppUtil;
import com.stolenvehicle.util.JsonUtil;

@Controller
public class LoginController {

	private static final Logger LOGGER = Logger
			.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, path = "/login")
	public ResponseEntity<String> login(@RequestBody String requestBody,
			HttpServletRequest request) {

		ResponseEntity<String> response;
		try {

			UserTo user = JsonUtil.toObject(requestBody, Constants.USER,
					UserTo.class);
			user = userService.authenticateUser(user);
			HttpSession session = request.getSession(true);
			session.setAttribute(Constants.USER, user);
			response = new ResponseEntity<String>(HttpStatus.OK);

		} catch (Exception ex) {

			LOGGER.error("Error while authenticating user with Request : "
					+ requestBody, ex);
			response = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/register")
	public ResponseEntity<String> register(@RequestBody String requestBody) {

		ResponseEntity<String> response;
		try {

			UserTo user = JsonUtil.toObject(requestBody, Constants.USER,
					UserTo.class);
			user = userService.registerUser(user);
			response = new ResponseEntity<String>(HttpStatus.OK);

		} catch (BusinessException ex) {

			LOGGER.error("Error while registernig  user with request : "
					+ requestBody, ex);
			if (ex.getMessage().equals(ExceptionConstants.DUPLICATE_KEY)) {

				response = new ResponseEntity<String>(
						JsonUtil.toJson(Constants.ERROR, new ErrorTo(
								ErrorEnum.USER_ALREADY_REGISTERED.getCode(),
								ErrorEnum.USER_ALREADY_REGISTERED.getMessage())),
						HttpStatus.BAD_REQUEST);

			} else {

				response = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}

		} catch (IllegalArgumentException ex) {

			LOGGER.error("Bad Request : " + requestBody, ex);
			response = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

		} catch (Exception ex) {

			LOGGER.error("Error while authenticating user with Request : "
					+ requestBody, ex);
			response = new ResponseEntity<String>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {
		ResponseEntity<String> response;
		boolean invalidateSession = AppUtil.invalidateSession(request);
		response = invalidateSession ? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return response;
	}
}
