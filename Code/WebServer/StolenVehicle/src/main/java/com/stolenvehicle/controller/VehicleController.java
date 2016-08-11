package com.stolenvehicle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.dto.SearchTo;
import com.stolenvehicle.dto.TheftInformationTo;
import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.dto.VehicleTo;
import com.stolenvehicle.exception.ExceptionProcessor;
import com.stolenvehicle.service.TheftInformationService;
import com.stolenvehicle.service.VehicleService;
import com.stolenvehicle.util.AppUtil;
import com.stolenvehicle.util.JsonUtil;

@Controller
public class VehicleController {

	private static final Logger LOGGER = Logger.getLogger(VehicleController.class);

	@Autowired
	private TheftInformationService theftInformationService;

	@Autowired
	private VehicleService vehicleService;

	@RequestMapping(method = RequestMethod.GET, value = "/searchForTheft")
	public ResponseEntity<String> searchForStolenVehicleByNumber(@RequestParam(name = "regNumber") String regNumber) {

		ResponseEntity<String> response = null;
		try {

			TheftInformationTo theftInformationTo = theftInformationService
					.getTheftInformationByVehicleRegistrationNumber(regNumber);

			response = new ResponseEntity<String>(JsonUtil.toJson(Constants.THEFT_INFO, theftInformationTo),
					HttpStatus.OK);

		} catch (Exception ex) {

			LOGGER.error("Error while find vehicle with id " + regNumber, ex);
			response = ExceptionProcessor.handleException(ex);

		} finally {

		}
		return response;
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST, value = "/searchForTheft")
	 * public ResponseEntity<String> searchForStolenVehicle(@RequestBody String
	 * request) {
	 * 
	 * ResponseEntity<String> response = null; try {
	 * 
	 * SearchTo searchTo = JsonUtil.toObject(request, Constants.SEARCH,
	 * SearchTo.class);
	 * 
	 * TheftInformationTo theftInformationTo = theftInformationService
	 * .getTheftInformationByVehicleRegistrationNumber(regNumber);
	 * 
	 * response = new
	 * ResponseEntity<String>(JsonUtil.toJson(Constants.THEFT_INFO,
	 * theftInformationTo), HttpStatus.OK);
	 * 
	 * } catch (Exception ex) {
	 * 
	 * LOGGER.error("Error while find vehicle with id " + regNumber, ex);
	 * response = ExceptionProcessor.handleException(ex);
	 * 
	 * } finally {
	 * 
	 * } return response; }
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/vehicles")
	public ResponseEntity<String> getRegisteredVehicles(HttpServletRequest request) {
		ResponseEntity<String> response = null;
		try {

			AppUtil.checkIfUserHasSession(request);
			UserTo user = AppUtil.getUserFromSession(request);
			List<VehicleTo> registeredVehicleForUser = vehicleService.getRegisteredVehicleForUser(user.getId());
			response = new ResponseEntity<>(JsonUtil.toJson(Constants.VEHICLES, registeredVehicleForUser),
					HttpStatus.OK);
		} catch (Exception ex) {

			LOGGER.error("Error while finding vehicles for user ", ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

		}
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/vehiclesTypes")
	public ResponseEntity<String> getVehicleTypes(@RequestParam(name = "countryId") String countryId,
			HttpServletRequest request) {
		ResponseEntity<String> response = null;
		try {

			List<String> vehicleTypes = vehicleService.getVehicleTypes(countryId);
			response = new ResponseEntity<>(JsonUtil.toJson(vehicleTypes), HttpStatus.OK);

		} catch (Exception ex) {

			LOGGER.error("Error while finding vehicle type ", ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

		}
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/vehicleMake")
	public ResponseEntity<String> getVehicleMakeTypes(@RequestParam(name = "countryId") String countryId,
			@RequestParam(name = "vehicleType") String vehicleType, HttpServletRequest request) {
		ResponseEntity<String> response = null;
		try {

			List<String> makeList = vehicleService.getVehicleMakeList(countryId, vehicleType);
			response = new ResponseEntity<>(JsonUtil.toJson(makeList), HttpStatus.OK);

		} catch (Exception ex) {

			LOGGER.error("Error while finding vehicle make ", ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

		}
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/vehicleModel")
	public ResponseEntity<String> getVehicleModelTypes(@RequestParam(name = "countryId") String countryId,
			@RequestParam(name = "vehicleType") String vehicleType,
			@RequestParam(name = "vehicleMake") String vehicleMake, HttpServletRequest request) {
		ResponseEntity<String> response = null;
		try {

			List<String> makeList = vehicleService.getVehicleModelList(countryId, vehicleType, vehicleMake);
			response = new ResponseEntity<>(JsonUtil.toJson(makeList), HttpStatus.OK);

		} catch (Exception ex) {

			LOGGER.error("Error while finding vehicle model ", ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

		}
		return response;
	}
}
