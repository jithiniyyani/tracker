package com.stolenvehicle.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.exception.ExceptionProcessor;
import com.stolenvehicle.service.TemplateService;
import com.stolenvehicle.util.AppUtil;
import com.stolenvehicle.util.JsonUtil;

@Controller
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private TemplateService templateService;

	@RequestMapping(method = RequestMethod.GET, path = "/ping")
	public ResponseEntity<String> ping() {
		LOGGER.debug("Entering ping");
		LOGGER.debug("Done with ping");
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

}
