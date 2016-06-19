package com.findmycar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.findmycar.dao.CountryDao;
import com.findmycar.to.Country;

@Service
public class CountryDaoImpl extends AbstractDao implements CountryDao {

	private final static String FIND_ALL_COUNTRIES = "select * from country;";

	private static final class GetCountyrListResultSetExtractorImp implements
			ResultSetExtractor<List<Country>> {

		@Override
		public List<Country> extractData(final ResultSet resultSet)
				throws SQLException {

			List<Country> countryList = new ArrayList<Country>();
			Country country = null;
			while (resultSet.next()) {
				country = new Country();
				country.setId(resultSet.getString(1));
				country.setCountryCode(resultSet.getString(2));
				country.setCountryName(resultSet.getString(3));
				countryList.add(country);

			}
			return countryList;
		}
	};

	@Override
	public Country findCountry(String countryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Country> getAllCountries() {

		return (List<Country>) this.fetch(FIND_ALL_COUNTRIES, null,
				new GetCountyrListResultSetExtractorImp());
	}

}
