package com.findmycar.service;

import java.util.List;

import com.findmycar.to.Car;

public interface CarService {

	List<Car> findCarsByUserId(String userId);

	Car createCarForUser(Car car, String userId);

	boolean deleteCar(String carId);

}