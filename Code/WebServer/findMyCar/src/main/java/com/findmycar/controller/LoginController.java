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
public class LoginController {

	private static final Logger LOGGER = Logger
			.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> login(@RequestBody final String requestBody,
			HttpServletRequest request) {

		User user = null;
		LOGGER.debug("Entering LoginController::login");
		ResponseEntity<String> response = null;
		try {
			user = JsonUtil.toObject(requestBody, "user", User.class);

			User userFromDb = userService.findUser(user);
			response = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
			// #TODO: validate user name and password
			if (userFromDb != null
					&& user.getPassword().equals(user.getPassword())) {
				// valid user
				// We create a session for the user
				if (user.getUsername().equals(userFromDb.getUsername())
						|| user.getUsername().equals(
								userFromDb.getEmailaddress())) {

					if (user.getPassword().equals(userFromDb.getPassword())) {
						HttpSession session = request.getSession(true);
						session.setAttribute("user", userFromDb);
						response = new ResponseEntity<String>(JsonUtil.toJson(
								"user", user), HttpStatus.OK);
					}

				}
			}
		} catch (Exception ex) {
			response = new ResponseEntity<String>(
					HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.debug("Error while authenticating ", ex);
		}
		LOGGER.debug("Completed LoginController::login");
		return response;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> logout(HttpServletRequest request) {

		LOGGER.debug("Entering LoginController::logout");
		ResponseEntity<String> response = null;
		try {
			HttpSession session = request.getSession(false);
			session.invalidate();
		} catch (Exception ex) {
			response = new ResponseEntity<String>(
					HttpStatus.INTERNAL_SERVER_ERROR);
			LOGGER.debug("Error while authenticating ", ex);
		}
		LOGGER.debug("Completed LoginController::logout");
		return response;
	}
}
