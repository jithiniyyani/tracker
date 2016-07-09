package com.findmycar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findmycar.dao.CarDao;
import com.findmycar.exception.BusinessException;
import com.findmycar.service.CarService;
import com.findmycar.to.Attachment;
import com.findmycar.to.Car;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDao carDao;

	@Override
	public List<Car> findCarsByUserId(String userId) {

		return carDao.findCarsByUserId(userId);
	}

	@Override
	public Car createCarForUser(Car car, String userId,List<Attachment> images) {

		return carDao.addCarForUser(car, userId,images);
	}

	@Override
	public boolean deleteCar(String carId) {
		// TODO Auto-generated method stub
		return carDao.deleteCar(carId);
	}

	@Override
	public boolean markCarAsStolen(String carId) throws BusinessException {
		
		boolean status = false;
		status = carDao.markCarAsStolen(carId);
		return status;
	}

	@Override
	public List<Car> getListOfStolenCars(String registrationNumber)
			throws BusinessException {

		return carDao.getStolenCars(registrationNumber);
	}

}
