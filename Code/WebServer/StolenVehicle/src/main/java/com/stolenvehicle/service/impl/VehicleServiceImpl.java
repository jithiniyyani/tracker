package com.stolenvehicle.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stolenvehicle.dao.VehicleDao;
import com.stolenvehicle.dto.VehicleTo;
import com.stolenvehicle.entity.Vehicle;
import com.stolenvehicle.exception.BusinessException;
import com.stolenvehicle.service.VehicleService;
import com.stolenvehicle.util.ConversionUtil;

@Service
public class VehicleServiceImpl implements VehicleService {

	private static final Logger LOGGER = Logger
			.getLogger(VehicleServiceImpl.class);

	@Autowired
	private VehicleDao vehicleDao;

	@Override
	public VehicleTo saveVehicle(VehicleTo vehicleTo) throws BusinessException {

		Vehicle vehicle = ConversionUtil.convertVehicleTo(vehicleTo);
		vehicleDao.saveVehicle(vehicle);
		vehicleTo.setId(vehicle.getId());
		return vehicleTo;
	}
}
