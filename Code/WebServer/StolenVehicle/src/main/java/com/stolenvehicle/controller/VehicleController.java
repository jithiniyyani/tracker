package com.stolenvehicle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stolenvehicle.constants.Constants;
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
}
