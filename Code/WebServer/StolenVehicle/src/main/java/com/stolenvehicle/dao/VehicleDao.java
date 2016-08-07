package com.stolenvehicle.dao;

import java.util.List;

import com.stolenvehicle.entity.Vehicle;
import com.stolenvehicle.exception.BusinessException;

public interface VehicleDao extends GenericDao {

	public Vehicle saveVehicle(Vehicle vehicle) throws BusinessException;

	public List<Vehicle> getRegisteredVehicleForUser(String userId) throws BusinessException;

}
