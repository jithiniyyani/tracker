package com.stolenvehicle.constants;

public enum SuccessEnum {

	ACTIVATE_ACCOUNT("200",
			"Kindly activate you account by clicking on the activation link in your mail box");

	private String code;
	private String message;

	SuccessEnum(String code, String message) {
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
