package com.findmycar.service;

import java.util.List;

import com.findmycar.exception.BusinessException;
import com.findmycar.to.Attachment;
import com.findmycar.to.Car;

public interface CarService {

	List<Car> findCarsByUserId(String userId);

	Car createCarForUser(Car car, String userId, List<Attachment> images);

	boolean deleteCar(String carId);

	boolean markCarAsStolen(String carId) throws BusinessException;

	List<Car> getListOfStolenCars(String registrationNumber)
			throws BusinessException;

}