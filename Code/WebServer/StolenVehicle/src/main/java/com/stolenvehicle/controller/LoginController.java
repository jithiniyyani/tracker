package com.stolenvehicle.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.service.UserService;
import com.stolenvehicle.util.JsonUtil;

@Controller
public class LoginController {

	private static final Logger LOGGER = Logger
			.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, path = "/login")
	public ResponseEntity<String> login(@RequestBody String requestBody) {

		ResponseEntity<String> response;
		try {

			UserTo user = JsonUtil.toObject(requestBody, "user", UserTo.class);
			user = userService.authenticateUser(user);
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

			UserTo user = JsonUtil.toObject(requestBody, "user", UserTo.class);
			user = userService.registerUser(user);
			response = new ResponseEntity<String>(HttpStatus.OK);

		} catch (Exception ex) {

			LOGGER.error("Error while authenticating user with Request : "
					+ requestBody, ex);
			response = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		return response;
	}
	
}
