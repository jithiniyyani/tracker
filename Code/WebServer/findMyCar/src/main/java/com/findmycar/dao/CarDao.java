package com.findmycar.dao;

import java.util.List;

import com.findmycar.exception.BusinessException;
import com.findmycar.to.Attachment;
import com.findmycar.to.Car;

public interface CarDao extends GenericDao {

	public Car saveCar();

	public boolean deleteCar(String carId);

	public List<Car> findCarsByUserId(String userId);

	public Car fetchCarById(String cardId);

	public Car addCarForUser(Car car, String userId, List<Attachment> images);

	public boolean markCarAsStolen(String carId) throws BusinessException;

	public List<Car> getStolenCars(String registrationNumber)
			throws BusinessException;

}
