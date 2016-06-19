package com.findmycar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.findmycar.service.CountryService;
import com.findmycar.to.Country;
import com.findmycar.util.JsonUtil;

@RestController
public class CountryController {

	@Autowired
	private CountryService countryService;

	@RequestMapping(value = "/countries", method = RequestMethod.GET, headers = "Accept=application/json")
	public String findAllCountries() {
		List<Country> allCountries = countryService.findAllCountries();
		return JsonUtil.toJson("countries", allCountries);
	}
}
