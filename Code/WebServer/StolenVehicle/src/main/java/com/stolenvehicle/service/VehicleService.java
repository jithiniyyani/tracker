package com.stolenvehicle.service;

import java.util.List;

import com.stolenvehicle.dto.VehicleTo;
import com.stolenvehicle.exception.BusinessException;

public interface VehicleService {

	public VehicleTo saveVehicle(VehicleTo vehicle) throws BusinessException;

	public List<VehicleTo> getRegisteredVehicleForUser(String userId) throws BusinessException;

}
