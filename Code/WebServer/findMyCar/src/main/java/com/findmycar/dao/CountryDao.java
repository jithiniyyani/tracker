package com.findmycar.dao;

import java.util.List;

import com.findmycar.to.Country;

public interface CountryDao  extends GenericDao{

	public Country findCountry(String countryName);

	public List<Country> getAllCountries();
}
