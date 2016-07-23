package com.stolenvehicle.dao;

import org.springframework.jdbc.core.ResultSetExtractor;

public interface GenericDao {

	void save(String insertQuery, Object[] queryParamterList);

	Object fetch(String query, Object[] queryParamterList, ResultSetExtractor resultSetExtractor);

}
