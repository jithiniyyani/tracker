package com.stolenvehicle.entity;

import java.sql.Timestamp;

import com.stolenvehicle.constants.TheftStatus;

public class TheftInformation {

	private String id;
	private String theft_location_cordinates;
	private String vehicle_id;
	private String country_id;
	private String rewards;
	private Timestamp theft_dateTime;
	private TheftStatus status;
	private String find_information_id;
	private AuditToken auditToken;
	private User user;
	private Vehicle vehicle;

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

	public Timestamp getTheft_dateTime() {
		return theft_dateTime;
	}

	public void setTheft_dateTime(Timestamp theft_dateTime) {
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

	public AuditToken getAuditToken() {
		return auditToken;
	}

	public void setAuditToken(AuditToken auditToken) {
		this.auditToken = auditToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
