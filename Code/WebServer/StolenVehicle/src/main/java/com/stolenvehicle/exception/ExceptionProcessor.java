package com.stolenvehicle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.ErrorEnum;
import com.stolenvehicle.constants.ExceptionConstants;
import com.stolenvehicle.dto.ErrorTo;
import com.stolenvehicle.util.JsonUtil;

/*
 * All error handling for exceptions go here
 */
public class ExceptionProcessor {

	public static ResponseEntity<String> handleException(Exception ex) {

		ResponseEntity<String> response = null;

		if (ex instanceof IllegalArgumentException) {

			if (ex.getMessage().equalsIgnoreCase(
					ExceptionConstants.INVALID_SESSION)) {

				response = new ResponseEntity<String>(JsonUtil.toJson(
						Constants.ERROR,
						new ErrorTo(ErrorEnum.INVALID_SESSION.getCode(),
								ErrorEnum.INVALID_SESSION.getMessage())),
						HttpStatus.FORBIDDEN);

			} else if (ex.getMessage().equalsIgnoreCase(
					ExceptionConstants.INVALID_JSON)) {

				response = new ResponseEntity<String>(JsonUtil.toJson(
						Constants.ERROR,
						new ErrorTo(ErrorEnum.INVALID_JSON.getCode(),
								ErrorEnum.INVALID_JSON.getMessage())),
						HttpStatus.BAD_REQUEST);
			} else if (ex.getMessage().equalsIgnoreCase(
					ExceptionConstants.EMPTY_INPUT)) {

				response = new ResponseEntity<String>(JsonUtil.toJson(
						Constants.ERROR,
						new ErrorTo(ErrorEnum.EMPTY_INPUT.getCode(),
								ErrorEnum.EMPTY_INPUT.getMessage())),
						HttpStatus.BAD_REQUEST);
			} else {

				response = new ResponseEntity<>(
						HttpStatus.INTERNAL_SERVER_ERROR);

			}
		} else if (ex instanceof BusinessException) {

			switch (ex.getMessage()) {
			case ExceptionConstants.USER_NOT_FOUND:

				response = new ResponseEntity<String>(JsonUtil.toJson(
						Constants.ERROR,
						new ErrorTo(ErrorEnum.USER_NOT_FOUND.getCode(),
								ErrorEnum.USER_NOT_FOUND.getMessage())),
						HttpStatus.FORBIDDEN);
				break;

			case ExceptionConstants.THEFT_INFO_NOT_FOUND:

				response = new ResponseEntity<String>(JsonUtil.toJson(
						Constants.ERROR, new ErrorTo(
								ErrorEnum.THEFT_INFO_NOT_FOUND.getCode(),
								ErrorEnum.THEFT_INFO_NOT_FOUND.getMessage())),
						HttpStatus.BAD_REQUEST);
				break;

			case ExceptionConstants.DUPLICATE_KEY:

				response = new ResponseEntity<String>(JsonUtil.toJson(
						Constants.ERROR,
						new ErrorTo(ErrorEnum.ENTITY_ALREADY_EXIST_IN_DB
								.getCode(),
								ErrorEnum.ENTITY_ALREADY_EXIST_IN_DB
										.getMessage())), HttpStatus.BAD_REQUEST);
				break;
			default:

				response = new ResponseEntity<String>(
						JsonUtil.toJson(Constants.ERROR, new ErrorTo(
								ErrorEnum.INTERNAL_SERVICE_ERROR.getCode(),
								ErrorEnum.INTERNAL_SERVICE_ERROR.getMessage())),
						HttpStatus.INTERNAL_SERVER_ERROR);
				break;
			}

		} else if (ex instanceof SystemException) {

			switch (ex.getMessage()) {
			case ExceptionConstants.SQL_EXCEPTION:

				response = new ResponseEntity<String>(
						JsonUtil.toJson(Constants.ERROR, new ErrorTo(
								ErrorEnum.INTERNAL_SERVICE_ERROR.getCode(),
								ErrorEnum.INTERNAL_SERVICE_ERROR.getMessage())),
						HttpStatus.INTERNAL_SERVER_ERROR);
				break;

			default:

				response = new ResponseEntity<String>(
						JsonUtil.toJson(Constants.ERROR, new ErrorTo(
								ErrorEnum.INTERNAL_SERVICE_ERROR.getCode(),
								ErrorEnum.INTERNAL_SERVICE_ERROR.getMessage())),
						HttpStatus.INTERNAL_SERVER_ERROR);
				break;

			}
		} else {

			response = new ResponseEntity<String>(JsonUtil.toJson(
					Constants.ERROR, new ErrorTo(
							ErrorEnum.INTERNAL_SERVICE_ERROR.getCode(),
							ErrorEnum.INTERNAL_SERVICE_ERROR.getMessage())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
