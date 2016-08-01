package com.stolenvehicle.dto;

import java.util.List;

import com.stolenvehicle.constants.FindStatusEnum;

public class FindInformationTo {

	private String id;
	private String find_location_cordinates;
	private String find_dateTime;
	private String locators_name;
	private String locators_email;
	private String locators_contactNumber;
	private String theft_information_id;
	private String vehicle_id;
	private String user_id;
	private FindStatusEnum findStatus;
	private List<AttachmentTo> attachments;

	public FindInformationTo(){
		
	}
	public FindInformationTo(String id, String locators_name,
			String locators_email, String locator_contactNumber,String find_location_cordinates) {
		this.id = id;
		this.locators_name = locators_name;
		this.locators_email = locators_email;
		this.locators_contactNumber = locator_contactNumber;
		this.find_location_cordinates = find_location_cordinates;
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

	public List<AttachmentTo> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachmentTo> attachments) {
		this.attachments = attachments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}
