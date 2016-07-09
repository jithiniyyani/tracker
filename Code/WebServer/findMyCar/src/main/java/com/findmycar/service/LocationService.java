package com.findmycar.service;

import java.util.List;

import com.findmycar.exception.BusinessException;
import com.findmycar.to.StolenLocation;

public interface LocationService {

	public boolean saveLocationOfStolenVehicle(StolenLocation location)
			throws BusinessException;

	public List<StolenLocation> getListStolenVehicleLocations() throws BusinessException;
}
