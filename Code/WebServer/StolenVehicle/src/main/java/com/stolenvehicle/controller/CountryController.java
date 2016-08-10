package com.stolenvehicle.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stolenvehicle.dto.CountryTo;
import com.stolenvehicle.exception.ExceptionProcessor;
import com.stolenvehicle.service.CountryService;
import com.stolenvehicle.util.JsonUtil;

@Controller
public class CountryController {

	private static final Logger LOGGER = Logger.getLogger(CountryController.class);

	@Autowired
	private CountryService countryService;

	@RequestMapping(method = RequestMethod.GET, value = "/countries")
	public ResponseEntity<String> getCountryList() {

		ResponseEntity<String> response = null;
		try {

			List<CountryTo> countryList = countryService.getCountryList();
			String json = JsonUtil.toJson(countryList);
			response = new ResponseEntity<String>(json, HttpStatus.OK);
		} catch (Exception ex) {

			LOGGER.error("Error while getting countries", ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

		}
		return response;
	}
}
