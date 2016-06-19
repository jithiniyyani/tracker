package com.findmycar.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.findmycar.service.CarService;
import com.findmycar.to.Car;
import com.findmycar.to.User;
import com.findmycar.util.JsonUtil;

@RestController
public class CarController {

	private static final Logger LOGGER = Logger.getLogger(CarController.class);

	@Autowired
	private CarService carService;

	@RequestMapping(value = "/cars", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getCars(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		List<Car> carlist = carService.findCarsByUserId(user.getUserId());
		return new ResponseEntity<String>(JsonUtil.toJson("cars", carlist),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/car", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> createCar(
		
			@RequestBody final String requestBody, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		ResponseEntity<String> response = null;
		Car car = null;
		try {
			car = JsonUtil.toObject(requestBody, "car", Car.class);
			car = carService.createCarForUser(car, user.getUserId());
			response = new ResponseEntity<String>(JsonUtil.toJson("car", car),
					HttpStatus.OK);
		} catch (Exception ex) {

			response = new ResponseEntity<String>(
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return response;
	}

	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public ResponseEntity<String> uploadCarImage(
			@RequestBody final String requestBody, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		ResponseEntity<String> response = null;
		Car car = null;
		try {
			response = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {

			response = new ResponseEntity<String>(
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return response;
	}

	@RequestMapping(value = "/car/{carId}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseEntity<String> deleteCar(@PathVariable final String carId,
			HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		ResponseEntity<String> response = null;
		Car car = null;
		try {
			LOGGER.debug("Deleted car with id " + carId);
			carService.deleteCar(carId);
			response = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {

			response = new ResponseEntity<String>(
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return response;
	}
}
