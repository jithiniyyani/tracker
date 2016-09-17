package com.stolenvehicle.constants;

public enum ErrorEnum {

	USER_ALREADY_REGISTERED("400",
			"User is already registered, Use different email address"), 
	USER_NOT_FOUND("403","User not registered"),
	INVALID_ACCOUNT_STATE("403","Kindly activate your account. If you still face problem contact customer service"),
	INVALID_PASSWORD("403","Kindly check your password"),
	INVALID_SESSION(
			"403", "Invalid session. Kindly login"),
	INVALID_JSON("400",
			"Invalid json kindly correct"),
	INTERNAL_SERVICE_ERROR("500","Opps something went wrong. Kindly contact customer service"),
	ENTITY_ALREADY_EXIST_IN_DB("400", "Entity already exists in db"),
	EMPTY_INPUT("400","Empty input"),
	THEFT_INFO_NOT_FOUND("400","No thefts found for searched vehicle"),
	EMAIL_ID_NOT_FOUND_FOR_RESET("400","Email id not found for password reset"),
	ACTIVATE_USER_ID_NOT_FOUND("400","Activation id not valid"),
	NO_THEFTS_FOR_VEHICLE("400","No thefts registered for this vehicle"),
	EMAIL_ID_ALREADY_REGISTERED("400","Email id is already registered with us"),
	OLD_PASSWORD_NOT_VALID("400","Old password is not valid, kindly check again");;

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
