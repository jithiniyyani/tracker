package com.stolenvehicle.dto;

import com.stolenvehicle.constants.VehicleEnum;

public class SearchTo {

	private String make;
	private String model;
	private String year_of_make;
	private String color;
	private String registrationNo;
	private String country_id;
	private VehicleEnum type;

	public VehicleEnum getType() {
		return type;
	}

	public void setType(VehicleEnum type) {
		this.type = type;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear_of_make() {
		return year_of_make;
	}

	public void setYear_of_make(String year_of_make) {
		this.year_of_make = year_of_make;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

}
