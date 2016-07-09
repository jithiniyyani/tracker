package com.findmycar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.findmycar.dao.StolenLocationDao;
import com.findmycar.exception.BusinessException;
import com.findmycar.to.Country;
import com.findmycar.to.StolenLocation;

@Service
public class StolenLocationDaoImpl extends AbstractDao implements
		StolenLocationDao {

	public static final String INSERT_STOLEN_VEHICHLE = "insert into stolen_location values(?,?,?,'system',now(),null,null)";

	private final static String FIND_ALL_STOLEN_VEHICLES = "select * from stolen_location;";

	@Override
	public boolean saveStolenLocation(StolenLocation location)
			throws BusinessException {

		boolean status = false;

		save(INSERT_STOLEN_VEHICHLE, new Object[] {
				UUID.randomUUID().toString(), location.getLocationCorindates(),
				location.getCarId() });
		status = true;
		return status;
	}

	
	private static final class GetStolenLocationResultSetExtractorImp implements
			ResultSetExtractor<List<StolenLocation>> {

		@Override
		public List<StolenLocation> extractData(final ResultSet resultSet)
				throws SQLException {

			List<StolenLocation> stolenLocationList = new ArrayList<StolenLocation>();
			StolenLocation stolenLocation = null;
			while (resultSet.next()) {
				stolenLocation = new StolenLocation();
				stolenLocation.setId(resultSet.getString(1));
				stolenLocation.setLocationCorindates(resultSet.getString(2));
				stolenLocation.setCarId(resultSet.getString(3));
				stolenLocationList.add(stolenLocation);

			}
			return stolenLocationList;
		}
	};
	@Override
	public List<StolenLocation> getListStolenVehicleLocations() throws BusinessException {
		
		return (List<StolenLocation>) this.fetch(FIND_ALL_STOLEN_VEHICLES, null,
				new GetStolenLocationResultSetExtractorImp());	
		
	}

}
