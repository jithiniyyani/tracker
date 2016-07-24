package com.stolenvehicle.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.Query;
import com.stolenvehicle.dao.TheftInformationDao;
import com.stolenvehicle.entity.TheftInformation;
import com.stolenvehicle.exception.BusinessException;

@Service
public class TheftInformationDaiImpl extends AbstractDao implements
		TheftInformationDao {

	private static final class TheftInformationResultSetExtractor implements
			ResultSetExtractor<TheftInformation> {

		@Override
		public TheftInformation extractData(final ResultSet resultSet)
				throws SQLException {

			TheftInformation theftInformation = null;
			if (resultSet.next()) {
				theftInformation = new TheftInformation();
				theftInformation.setId(resultSet.getString("ti.id"));
				theftInformation.setTheft_location_cordinates(resultSet
						.getString("ti.theft_location_cordinates"));
			}
			return theftInformation;
		}
	};

	@Override
	public TheftInformation saveTheftInformation(
			TheftInformation theftInformation) throws BusinessException {

		String id = UUID.randomUUID().toString();
		theftInformation.setId(id);
		this.save(
				Query.SAVE_THEFT,
				new Object[] { theftInformation.getId(),
						theftInformation.getTheft_location_cordinates(),
						theftInformation.getVehicle_id(),
						theftInformation.getCountry_id(),
						theftInformation.getRewards(),
						theftInformation.getTheft_dateTime(),
						theftInformation.getStatus().toString(), null,
						Constants.APP_NAME });
		return theftInformation;
	}

	@Override
	public TheftInformation getTheftInformation(String theftInformationId)
			throws BusinessException {

		final Object theftInfoOjbect = this.fetch(Query.GET_THEFT_INFO,
				new Object[] { theftInformationId },
				new TheftInformationResultSetExtractor());
		TheftInformation theftInformation = (TheftInformation) theftInfoOjbect;
		return theftInformation;
	}

}
