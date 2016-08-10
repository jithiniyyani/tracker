package com.stolenvehicle.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.Query;
import com.stolenvehicle.dao.CountryDao;
import com.stolenvehicle.entity.Country;
import com.stolenvehicle.exception.BusinessException;

@Service
public class CountryDaoImpl extends AbstractDao implements CountryDao {

	private static final class CountryResultSetExtractor implements ResultSetExtractor<List<Country>> {

		@Override
		public List<Country> extractData(final ResultSet resultSet) throws SQLException {

			List<Country> countryList = new ArrayList<Country>();
			while (resultSet.next()) {

				Country country = new Country();
				country.setCountry(resultSet.getString("country"));
				country.setCountry_code(resultSet.getString("country_code"));
				countryList.add(country);
			}
			return countryList;
		}
	};

	@Override
	public List<Country> getCountryList() throws BusinessException {

		final Object countryList = this.fetch(Query.GET_COUNTRY_LIST, new Object[] {}, new CountryResultSetExtractor());
		return (List<Country>) countryList;
	}

}
