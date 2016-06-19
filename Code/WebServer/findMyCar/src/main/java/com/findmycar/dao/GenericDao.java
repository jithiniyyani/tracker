package com.findmycar.dao;

import org.springframework.jdbc.core.ResultSetExtractor;



/**
 * This Interface provides generic methods for performing CRUD based operations
 *
 * @author Capgemini
 */
public interface GenericDao {

	
	/**
	 * The method performs Save operation.
	 *
	 * @param insertQuery
	 *            insert query 
	 * @param queryParamterList
	 *            query paramter list 
	 * @return  
	 */
	void save(String insertQuery,Object[] queryParamterList);
	
	/**
	 * The method performs Fetch operation returning Object.
	 *
	 * @param query
	 *            query 
	 * @param queryParamterList
	 *            query paramter list 
	 * @param resultSetExtractor
	 *            result set extractor 
	 * @return the object 
	 */
	Object fetch(String query,Object[] queryParamterList, ResultSetExtractor resultSetExtractor);
	
}
