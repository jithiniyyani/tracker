package com.findmycar.to;

public class Country {

	private String id;

	private String countryName;

	private String countryCode;

	private AccessDetails accessDetails;
	
	public Country() {

	}

	public Country(String id, String countryName, String countryCode) {
		this.id = id;
		this.countryCode = countryCode;
		this.countryName = countryName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AccessDetails getAccessDetails() {
		return accessDetails;
	}

	public void setAccessDetails(AccessDetails accessDetails) {
		this.accessDetails = accessDetails;
	}

	
}
