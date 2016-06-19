package com.findmycar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findmycar.dao.CarDao;
import com.findmycar.service.CarService;
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
	public Car createCarForUser(Car car, String userId) {

		return carDao.addCarForUser(car, userId);
	}

	@Override
	public boolean deleteCar(String carId) {
		// TODO Auto-generated method stub
		return carDao.deleteCar(carId);
	}

}
