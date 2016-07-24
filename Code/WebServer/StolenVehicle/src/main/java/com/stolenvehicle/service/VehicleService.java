package com.stolenvehicle.service;

import com.stolenvehicle.dto.VehicleTo;
import com.stolenvehicle.exception.BusinessException;

public interface VehicleService {

	public VehicleTo saveVehicle(VehicleTo vehicle) throws BusinessException;
}
