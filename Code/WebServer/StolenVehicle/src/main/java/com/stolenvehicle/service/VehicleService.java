package com.stolenvehicle.service;

import java.util.List;

import com.stolenvehicle.dto.VehicleTo;
import com.stolenvehicle.exception.BusinessException;

public interface VehicleService {

	public VehicleTo saveVehicle(VehicleTo vehicle) throws BusinessException;

	public List<VehicleTo> getRegisteredVehicleForUser(String userId) throws BusinessException;

	public List<String> getVehicleTypes(String countryId) throws BusinessException;

	public List<String> getVehicleMakeList(String countryId, String vehicleType) throws BusinessException;

	public List<String> getVehicleModelList(String countryId, String vehicleType, String makeType)
			throws BusinessException;

}
