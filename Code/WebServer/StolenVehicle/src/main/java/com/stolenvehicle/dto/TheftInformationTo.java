package com.stolenvehicle.dto;

import com.stolenvehicle.constants.TheftStatus;

public class TheftInformationTo {

	private String id;
	private String theft_location_cordinates;
	private String vehicle_id;
	private String country_id;
	private String rewards;
	private String theft_dateTime;
	private TheftStatus status;
	private String find_information_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTheft_location_cordinates() {
		return theft_location_cordinates;
	}

	public void setTheft_location_cordinates(String theft_location_cordinates) {
		this.theft_location_cordinates = theft_location_cordinates;
	}

	public String getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public String getTheft_dateTime() {
		return theft_dateTime;
	}

	public void setTheft_dateTime(String theft_dateTime) {
		this.theft_dateTime = theft_dateTime;
	}

	public TheftStatus getStatus() {
		return status;
	}

	public void setStatus(TheftStatus status) {
		this.status = status;
	}

	public String getFind_information_id() {
		return find_information_id;
	}

	public void setFind_information_id(String find_information_id) {
		this.find_information_id = find_information_id;
	}

	private VehicleTo vehicle;

	public VehicleTo getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleTo vehicle) {
		this.vehicle = vehicle;
	}

}
