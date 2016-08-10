package com.stolenvehicle.dao;

import java.util.List;

import com.stolenvehicle.entity.Country;
import com.stolenvehicle.exception.BusinessException;

public interface CountryDao extends GenericDao {

	public List<Country> getCountryList() throws BusinessException;
}
