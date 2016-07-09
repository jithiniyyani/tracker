package com.findmycar.dao;

import java.util.List;

import com.findmycar.exception.BusinessException;
import com.findmycar.to.StolenLocation;

public interface StolenLocationDao extends GenericDao {

	public boolean saveStolenLocation(StolenLocation location)
			throws BusinessException;

	public List<StolenLocation> getListStolenVehicleLocations() throws BusinessException;
}
