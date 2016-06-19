package com.findmycar.to;

public class Address {

	private String row1;

	private String city;

	private String state;

	private String postCode;

	private Country country;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Address() {

	}

	public Address(String row1, String city, String state, String postCode,
			Country country) {
		this.row1 = row1;
		this.city = city;
		this.state = state;
		this.postCode = postCode;
		this.country = country;
	}

	public String getRow1() {
		return row1;
	}

	public void setRow1(String row1) {
		this.row1 = row1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}
