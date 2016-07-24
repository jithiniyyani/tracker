package com.stolenvehicle.dao.impl;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.ExceptionConstants;
import com.stolenvehicle.dao.GenericDao;
import com.stolenvehicle.exception.BusinessException;
import com.stolenvehicle.exception.SystemException;

public abstract class AbstractDao implements GenericDao {

	private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);
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
	public void save(final String insertQuery, final Object[] queryParamterList)
			throws BusinessException {

		try {

			final JdbcTemplate jdbcTemplate = getJdbcTemplate();
			jdbcTemplate.update(insertQuery, queryParamterList);

		} catch (DuplicateKeyException ex) {

			throw new BusinessException(ExceptionConstants.DUPLICATE_KEY, ex);

		} catch (DataAccessException ex) {

			throw new SystemException(Constants.SQL_EXCEPTION, ex);

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

			throw new SystemException(Constants.SQL_EXCEPTION, ex);

		}

		return result;
	}

}
