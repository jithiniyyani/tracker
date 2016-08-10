package com.stolenvehicle.dto;

public class CountryTo {

	private String country_code;

	private String country;

	public CountryTo() {

	}

	public CountryTo(String country, String country_code) {
		this.country = country;
		this.country_code = country_code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
