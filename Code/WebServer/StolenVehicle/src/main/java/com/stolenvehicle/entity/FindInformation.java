package com.stolenvehicle.entity;

import com.stolenvehicle.constants.FindStatusEnum;

public class FindInformation {

	private String id;
	private String find_location_cordinates;
	private String find_dateTime;
	private String locators_name;
	private String locators_email;
	private String locators_contactNumber;
	private String theft_information_id;
	private FindStatusEnum findStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFind_location_cordinates() {
		return find_location_cordinates;
	}

	public void setFind_location_cordinates(String find_location_cordinates) {
		this.find_location_cordinates = find_location_cordinates;
	}

	public String getFind_dateTime() {
		return find_dateTime;
	}

	public void setFind_dateTime(String find_dateTime) {
		this.find_dateTime = find_dateTime;
	}

	public String getLocators_name() {
		return locators_name;
	}

	public void setLocators_name(String locators_name) {
		this.locators_name = locators_name;
	}

	public String getLocators_email() {
		return locators_email;
	}

	public void setLocators_email(String locators_email) {
		this.locators_email = locators_email;
	}

	public String getLocators_contactNumber() {
		return locators_contactNumber;
	}

	public void setLocators_contactNumber(String locators_contactNumber) {
		this.locators_contactNumber = locators_contactNumber;
	}

	public String getTheft_information_id() {
		return theft_information_id;
	}

	public void setTheft_information_id(String theft_information_id) {
		this.theft_information_id = theft_information_id;
	}

	public FindStatusEnum getFindStatus() {
		return findStatus;
	}

	public void setFindStatus(FindStatusEnum findStatus) {
		this.findStatus = findStatus;
	}

}
