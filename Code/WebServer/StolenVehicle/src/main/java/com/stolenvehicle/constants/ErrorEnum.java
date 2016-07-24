package com.stolenvehicle.constants;

public enum ErrorEnum {

	USER_ALREADY_REGISTERED("400",
			"User is already registered, Use different email address"), 
	INVALID_SESSION(
			"403", "Invalid session. Kindly login"),
	JSON_NOT_CORRECT("400",
			"Invalid json kindly corrrect"),
	INTERNAL_SERVICE_ERROR("500","Opps something went wrong. Kindly contact customer service");

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
