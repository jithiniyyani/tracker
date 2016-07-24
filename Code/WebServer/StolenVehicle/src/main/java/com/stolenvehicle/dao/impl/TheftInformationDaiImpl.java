package com.stolenvehicle.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.Query;
import com.stolenvehicle.constants.TheftStatus;
import com.stolenvehicle.constants.VehicleEnum;
import com.stolenvehicle.dao.TheftInformationDao;
import com.stolenvehicle.entity.TheftInformation;
import com.stolenvehicle.entity.User;
import com.stolenvehicle.entity.Vehicle;
import com.stolenvehicle.exception.BusinessException;

@Service
public class TheftInformationDaiImpl extends AbstractDao implements
		TheftInformationDao {

	private static final class TheftInformationResultSetExtractor implements
			ResultSetExtractor<TheftInformation> {

		@Override
		public TheftInformation extractData(final ResultSet resultSet)
				throws SQLException {

			// ti.id,u.name,
			// v.registrationNo,ti.theft_dateTime,ti.theft_location_cordinates,v.type,v.make,v.model,v.year_of_make
			TheftInformation theftInformation = null;
			if (resultSet.next()) {
				theftInformation = new TheftInformation();
				theftInformation.setId(resultSet.getString("ti.id"));
				theftInformation.setTheft_dateTime(resultSet
						.getTimestamp("ti.theft_dateTime"));
				theftInformation.setTheft_location_cordinates(resultSet
						.getString("ti.theft_location_cordinates"));

				User user = new User();
				user.setName(resultSet.getString("u.name"));
				theftInformation.setUser(user);
				Vehicle vehicle = new Vehicle();
				vehicle.setType(VehicleEnum.valueOf(resultSet
						.getString("v.type")));
				vehicle.setModel(resultSet.getString("v.model"));
				vehicle.setYear_of_make((resultSet.getString("v.year_of_make")));
				vehicle.setRegistrationNo(resultSet
						.getString("v.registrationNo"));
				theftInformation.setVehicle(vehicle);

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

		final Object theftInfoOjbect = this.fetch(Query.GET_THEFT_INFO_BY_ID,
				new Object[] { theftInformationId },
				new TheftInformationResultSetExtractor());
		TheftInformation theftInformation = (TheftInformation) theftInfoOjbect;
		return theftInformation;
	}

	@Override
	public TheftInformation getTheftInformationByVehicleRegistrationNumber(
			String registrationNumber) throws BusinessException {

		final Object theftInfoOjbect = this.fetch(
				Query.GET_THEFT_INFO_BY_REGISTRATION_NUMBER, new Object[] { "%"
						+ registrationNumber + "%" },
				new TheftInformationResultSetExtractor());
		TheftInformation theftInformation = (TheftInformation) theftInfoOjbect;
		return theftInformation;
	}

	@Override
	public boolean updateTheftInformationStatus(String theftId,
			TheftStatus theftStatus) throws BusinessException {

		boolean status = false;
		this.save(Query.UPDATE_THEFT_INFO_STATUS, new Object[] { theftId,
				theftStatus.toString() });
		status = true;
		return status;
	}

}
