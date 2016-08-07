package com.stolenvehicle.service.impl;

import java.util.ArrayList;
import java.util.List;

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

	private static final Logger LOGGER = Logger.getLogger(VehicleServiceImpl.class);

	@Autowired
	private VehicleDao vehicleDao;

	@Override
	public VehicleTo saveVehicle(VehicleTo vehicleTo) throws BusinessException {

		Vehicle vehicle = ConversionUtil.convertVehicleTo(vehicleTo);
		vehicleDao.saveVehicle(vehicle);
		vehicleTo.setId(vehicle.getId());
		return vehicleTo;
	}

	@Override
	public List<VehicleTo> getRegisteredVehicleForUser(String userId) throws BusinessException {

		List<VehicleTo> vehicles = new ArrayList<>();
		List<Vehicle> registeredVehicleForUser = vehicleDao.getRegisteredVehicleForUser(userId);
		for (Vehicle vehicle : registeredVehicleForUser) {

			vehicles.add(ConversionUtil.convertVehicleEntity(vehicle));
		}
		return vehicles;
	}
}
