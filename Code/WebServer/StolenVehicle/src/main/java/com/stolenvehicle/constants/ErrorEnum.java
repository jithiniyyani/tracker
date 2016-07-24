package com.stolenvehicle.constants;

public enum ErrorEnum {

	USER_ALREADY_REGISTERED("400",
			"User is already registered, Use different email address");

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
