package com.stolenvehicle.dao;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.stolenvehicle.exception.BusinessException;

public interface GenericDao {

	void save(String insertQuery, Object[] queryParamterList) throws BusinessException;

	Object fetch(String query, Object[] queryParamterList, ResultSetExtractor resultSetExtractor);

}
