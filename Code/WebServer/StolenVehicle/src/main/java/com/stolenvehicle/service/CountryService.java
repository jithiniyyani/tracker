package com.stolenvehicle.service;

import java.util.List;

import com.stolenvehicle.dto.CountryTo;
import com.stolenvehicle.exception.BusinessException;

public interface CountryService {

	public List<CountryTo> getCountryList() throws BusinessException;
}
