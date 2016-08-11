package com.stolenvehicle.dao;

import java.util.List;

import com.stolenvehicle.entity.Vehicle;
import com.stolenvehicle.exception.BusinessException;

public interface VehicleDao extends GenericDao {

	public Vehicle saveVehicle(Vehicle vehicle) throws BusinessException;

	public List<Vehicle> getRegisteredVehicleForUser(String userId) throws BusinessException;

	public List<String> getVehicleTypes(String countryId) throws BusinessException;

	public List<String> getVehicleMakeList(String countryId, String vehicleType) throws BusinessException;

	public List<String> getVehicleModelList(String countryId, String vehicleType, String vehicleMake)
			throws BusinessException;

}
