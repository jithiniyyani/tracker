package com.findmycar.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.findmycar.contants.GenricConstants;
import com.findmycar.service.AttachmentService;
import com.findmycar.service.CarService;
import com.findmycar.service.FileService;
import com.findmycar.service.LocationService;
import com.findmycar.to.Attachment;
import com.findmycar.to.Car;
import com.findmycar.to.Image;
import com.findmycar.to.StolenLocation;
import com.findmycar.to.User;
import com.findmycar.util.JsonUtil;

@RestController
public class CarController {

	private static final Logger LOGGER = Logger.getLogger(CarController.class);

	@Autowired
	private CarService carService;

	@Autowired
	private FileService fileService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private LocationService locationService;

	// hardocding now we need to make this configurable
	private String basePath = "D:/tmp/attachments";

	@RequestMapping(value = "/cars", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getCars(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		List<Car> carlist = carService.findCarsByUserId(user.getUserId());
		return new ResponseEntity<String>(JsonUtil.toJson("cars", carlist),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/car", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> addVechile(

	@RequestBody final String requestBody, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		ResponseEntity<String> response = null;
		Car car = null;
		List<Attachment> images = null;
		try {
			images = (List<Attachment>) session
					.getAttribute(GenricConstants.ATTACHMENTS);

			car = JsonUtil.toObject(requestBody, "car", Car.class);
			car = carService.createCarForUser(car, user.getUserId(), images);
			images.clear();
			session.removeAttribute(GenricConstants.ATTACHMENTS);
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
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		List<Attachment> attachmentList = null;
		Object attribute = session.getAttribute(GenricConstants.ATTACHMENTS);
		if (attribute == null) {
			// create a new list
			attachmentList = new ArrayList<>();
			session.setAttribute(GenricConstants.ATTACHMENTS, attachmentList);
		} else {
			attachmentList = (ArrayList<Attachment>) attribute;
		}
		User user = (User) session.getAttribute("user");
		ResponseEntity<String> response = null;
		byte[] bytes;
		String baseFolderForUser = basePath + "/" + user.getUserId();
		String folder = baseFolderForUser + "/" + UUID.randomUUID().toString();
		try {
			if (!file.isEmpty()) {
				bytes = file.getBytes();
				fileService.writeToFile(folder, file.getOriginalFilename(),
						bytes);
				System.out.println(String.format("receive %s ",
						file.getOriginalFilename()));
				attachmentList.add(new Attachment(file.getOriginalFilename(),
						folder));
			}
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

	@RequestMapping(value = "/image/{imageId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getImage(@PathVariable String imageId) {

		Attachment attachment = attachmentService.fetchAttachmentById(imageId);
		byte[] binaryFile = fileService.getBinaryFile(attachment
				.getAttachmentPath() + "/" + attachment.getAttachmentName());
		Image image = new Image(attachment.getAttachmentName(), Base64
				.getEncoder().encodeToString(binaryFile));
		return new ResponseEntity<String>(JsonUtil.toJson(image), HttpStatus.OK);
	}

	@RequestMapping(value = "/saveLostLocation", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> saveLostLocation(
			@RequestBody final String requestBody, HttpServletRequest request) {

		StolenLocation location = null;
		try {

			location = JsonUtil.toObject(requestBody, "stolenLocation",
					StolenLocation.class);
			locationService.saveLocationOfStolenVehicle(location);
			carService.markCarAsStolen(location.getCarId());

		} catch (Exception ex) {

			LOGGER.error("Error while saving location", ex);

		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/getLostLocation", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getStolenLocation() {

		List<StolenLocation> listOfStolenCar = null;
		try {
			listOfStolenCar = locationService.getListStolenVehicleLocations();

		} catch (Exception ex) {

			LOGGER.error("Error while getting location", ex);

		}
		return new ResponseEntity<String>(JsonUtil.toJson("stolenLocations",
				listOfStolenCar), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/getStolenCars", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getStolenCars(
			@RequestParam(name = "regNumber") String registrationNumber) {

		List<Car> listOfStolenCars = null;
		try {

			listOfStolenCars = carService
					.getListOfStolenCars(registrationNumber);
		} catch (Exception ex) {

			LOGGER.error("Error" + " while getting location", ex);

		}
		return new ResponseEntity<String>(JsonUtil.toJson("cars",
				listOfStolenCars), HttpStatus.OK);
	}
	
}
