package com.findmycar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findmycar.dao.CountryDao;
import com.findmycar.service.CountryService;
import com.findmycar.to.Country;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countryDao;

	@Override
	public Country findCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Country> findAllCountries() {
		return countryDao.getAllCountries();
	}

}
