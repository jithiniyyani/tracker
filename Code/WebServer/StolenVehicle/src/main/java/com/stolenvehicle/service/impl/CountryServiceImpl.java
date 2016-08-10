package com.stolenvehicle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stolenvehicle.dao.CountryDao;
import com.stolenvehicle.dto.CountryTo;
import com.stolenvehicle.entity.Country;
import com.stolenvehicle.exception.BusinessException;
import com.stolenvehicle.service.CountryService;
import com.stolenvehicle.util.ConversionUtil;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countryDao;

	@Override
	public List<CountryTo> getCountryList() throws BusinessException {
		// TODO Auto-generated method stub
		List<Country> countryList = countryDao.getCountryList();
		return ConversionUtil.convertCountryEntityList(countryList);
	}

}
