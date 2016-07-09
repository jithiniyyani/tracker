package com.findmycar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findmycar.dao.StolenLocationDao;
import com.findmycar.exception.BusinessException;
import com.findmycar.service.LocationService;
import com.findmycar.to.StolenLocation;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private StolenLocationDao stolenLocationDao;

	@Override
	public boolean saveLocationOfStolenVehicle(StolenLocation location)
			throws BusinessException {

		return stolenLocationDao.saveStolenLocation(location);
	}

	@Override
	public List<StolenLocation> getListStolenVehicleLocations() throws BusinessException {

		return stolenLocationDao.getListStolenVehicleLocations();
	}

}
