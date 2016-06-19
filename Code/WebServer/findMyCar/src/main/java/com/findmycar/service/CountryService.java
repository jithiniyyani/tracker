package com.findmycar.service;

import java.util.List;

import com.findmycar.to.Country;

public interface CountryService {

	public Country findCountry();

	public List<Country> findAllCountries();

}
