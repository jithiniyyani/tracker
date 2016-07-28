package com.stolenvehicle.constants;

public enum ErrorEnum {

	USER_ALREADY_REGISTERED("400",
			"User is already registered, Use different email address"), 
	USER_NOT_FOUND("403","User not registered"),
	INVALID_SESSION(
			"403", "Invalid session. Kindly login"),
	INVALID_JSON("400",
			"Invalid json kindly corrrect"),
	INTERNAL_SERVICE_ERROR("500","Opps something went wrong. Kindly contact customer service"),
	ENTITY_ALREADY_EXIST_IN_DB("400", "Entity already exists in db"),
	EMPTY_INPUT("400","Empty input"),
	THEFT_INFO_NOT_FOUND("400","Theft Info not found");

	private String code;
	private String message;

	ErrorEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
