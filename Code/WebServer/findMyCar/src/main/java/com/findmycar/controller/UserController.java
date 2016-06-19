package com.findmycar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.findmycar.service.UserService;
import com.findmycar.to.User;
import com.findmycar.util.JsonUtil;

@RestController
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getUser(HttpServletRequest request) {

		User user = null;
		HttpSession session = request.getSession(false);
		user = (User) session.getAttribute("user");
		return new ResponseEntity<String>(JsonUtil.toJson("user", user),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> registerUser(
			@RequestBody final String requestBody,HttpServletRequest request) {

		User user = null;
		user = JsonUtil.toObject(requestBody, "user", User.class);
		ResponseEntity<String> response = null;
		if (userService.checkIfUserExists(user)) {

			LOGGER.error("User already exists");
			response = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

		} else {

			LOGGER.error("New user registering him");
			user = userService.createUser(user);
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			response = new ResponseEntity<String>(
					JsonUtil.toJson("user", user), HttpStatus.OK);
		}
		return response;
	}

}
