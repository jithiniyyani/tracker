package com.findmycar.dao.impl;

import javax.sql.DataSource;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.findmycar.contants.ErrorConstants;
import com.findmycar.dao.GenericDao;
import com.findmycar.exception.SystemException;

/**
 * This is an abstract class which provides implementation for
 * GenericDao.
 * 
 * @author Capgemini
 */
public abstract class AbstractDao implements GenericDao {

	/**
	 * Data source variable.
	 */
	@Autowired
	private DataSource dataSource;

	/**
	 * Gets the jdbc template.
	 *
	 * @return the jdbc template
	 */
	private JdbcTemplate getJdbcTemplate() {

		return new JdbcTemplate(dataSource);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ec.darwin.common.dao.GenericDao#save(java.lang.String,
	 * java.lang.Object[])
	 */
	public void save(final String insertQuery, final Object[] queryParamterList) {

		try {
		
			final JdbcTemplate jdbcTemplate = getJdbcTemplate();
			jdbcTemplate.update(insertQuery, queryParamterList);
		
		} catch (DataAccessException ex) {

			throw new SystemException(ErrorConstants.SQL_EXCEPTION, ex);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ec.darwin.common.dao.GenericDao#fetch(java.lang.String,
	 * java.lang.Object[], org.springframework.jdbc.core.ResultSetExtractor)
	 */
	public Object fetch(final String query, final Object[] queryParamterList,
			final ResultSetExtractor resultSetExtractor) {

		Object result = null;

		try {

			final JdbcTemplate jdbcTemplate = getJdbcTemplate();
			result = jdbcTemplate.query(query, queryParamterList,
					resultSetExtractor);
		} catch (DataAccessException ex) {

			throw new SystemException(ErrorConstants.SQL_EXCEPTION, ex);

		}

		return result;
	}

}
