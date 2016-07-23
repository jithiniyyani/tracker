package com.stolenvehicle.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stolenvehicle.service.UserService;

@Controller
public class LoginController {

	private static final Logger LOGGER = Logger
			.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, path = "/login")
	public ResponseEntity<String> login(@RequestBody String requestBody) {

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
