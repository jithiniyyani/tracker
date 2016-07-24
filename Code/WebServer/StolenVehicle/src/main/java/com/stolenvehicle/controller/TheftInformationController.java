package com.stolenvehicle.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.ErrorEnum;
import com.stolenvehicle.constants.ExceptionConstants;
import com.stolenvehicle.dto.ErrorTo;
import com.stolenvehicle.dto.TheftInformationTo;
import com.stolenvehicle.service.TheftInformationService;
import com.stolenvehicle.service.VehicleService;
import com.stolenvehicle.util.AppUtil;
import com.stolenvehicle.util.JsonUtil;

@Controller
public class TheftInformationController {

	private static final Logger LOGGER = Logger
			.getLogger(TheftInformationController.class);

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private TheftInformationService theftInformationService;

	@RequestMapping(method = RequestMethod.POST, path = "/registerTheft")
	public ResponseEntity<String> reportTheft(@RequestBody String jsonRequest,
			HttpServletRequest request) {

		ResponseEntity<String> response = null;
		try {

			AppUtil.checkIfUserHasSession(request);
			TheftInformationTo theftInformationTo = JsonUtil
					.toObject(jsonRequest, Constants.THEFT_INFO,
							TheftInformationTo.class);

			vehicleService.saveVehicle(theftInformationTo.getVehicle());
			theftInformationTo.setVehicle_id(theftInformationTo.getVehicle()
					.getId());
			theftInformationService.saveTheftInformation(theftInformationTo);

			response = new ResponseEntity<String>(JsonUtil.toJson(
					Constants.THEFT_INFO, theftInformationTo), HttpStatus.OK);
		} catch (IllegalArgumentException ex) {

			LOGGER.error("Error while registering theft", ex);
			if (ex.getMessage().equalsIgnoreCase(
					ExceptionConstants.INVALID_SESSION)) {

				response = new ResponseEntity<String>(JsonUtil.toJson(
						Constants.ERROR,
						new ErrorTo(ErrorEnum.INVALID_SESSION.getCode(),
								ErrorEnum.INVALID_SESSION.getMessage())),
						HttpStatus.FORBIDDEN);

			} else {

				response = new ResponseEntity<String>(JsonUtil.toJson(
						Constants.ERROR,
						new ErrorTo(ErrorEnum.JSON_NOT_CORRECT.getCode(),
								ErrorEnum.JSON_NOT_CORRECT.getMessage())),
						HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {

			LOGGER.error("Error while registering theft " + jsonRequest, ex);
			response = new ResponseEntity<String>(JsonUtil.toJson(
					Constants.ERROR, new ErrorTo(
							ErrorEnum.INTERNAL_SERVICE_ERROR.getCode(),
							ErrorEnum.INTERNAL_SERVICE_ERROR.getMessage())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/getTheftInfo")
	public ResponseEntity<String> getTheftInformation(
			@RequestParam(name = "theftId") String theftId) {
		ResponseEntity<String> response = null;
		try {

			TheftInformationTo theftInformation = theftInformationService
					.getTheftInformation(theftId);
			response = new ResponseEntity<String>(JsonUtil.toJson(
					Constants.THEFT_INFO, theftInformation), HttpStatus.OK);

		} catch (Exception ex) {

			LOGGER.error("Error while getting theft info with request id "
					+ theftId, ex);
			response = new ResponseEntity<String>(JsonUtil.toJson(
					Constants.ERROR, new ErrorTo(
							ErrorEnum.INTERNAL_SERVICE_ERROR.getCode(),
							ErrorEnum.INTERNAL_SERVICE_ERROR.getMessage())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;

	}

}
