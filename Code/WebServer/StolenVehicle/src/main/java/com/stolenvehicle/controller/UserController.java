package com.stolenvehicle.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Value("#{properties['max_message_length']}")
	private String maxMessagelength;

	@RequestMapping(method = RequestMethod.GET, path = "/ping")
	public ResponseEntity<String> ping() {
		LOGGER.debug("Entering ping");
		LOGGER.debug("Done with ping");
		return new ResponseEntity<String>("Test " + maxMessagelength, HttpStatus.OK);

	}
}
