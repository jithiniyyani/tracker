package com.stolenvehicle.dao.impl;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.Query;
import com.stolenvehicle.dao.VehicleDao;
import com.stolenvehicle.entity.Vehicle;
import com.stolenvehicle.exception.BusinessException;

@Service
public class VehicleDaoImpl extends AbstractDao implements VehicleDao {

	private static final Logger LOGGER = Logger.getLogger(VehicleDaoImpl.class);

	@Override
	public Vehicle saveVehicle(Vehicle vehicle) throws BusinessException {
		String id = UUID.randomUUID().toString();
		vehicle.setId(id);
		this.save(
				Query.SAVE_VEHICLE,
				new Object[] { vehicle.getId(), vehicle.getType().toString(),
						vehicle.getMake(), vehicle.getModel(),
						vehicle.getYear_of_make(), vehicle.getColor(),
						vehicle.getRegistrationNo(), vehicle.getExtra_info(),
						vehicle.isStolen() ? "true" : "false",
						vehicle.getUser_id(), vehicle.getCountry_id(),
						Constants.APP_NAME });
		return vehicle;
	}

}
