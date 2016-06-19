package com.findmycar.to;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.findmycar.contants.UserType;

public class User {

	// business attributes
	private String emailaddress;

	private String password;

	private String name;

	private String username;

	private String gender;

	private String passport;

	private String contactNumber;

	private Address address;

	private boolean termsAndConditions;

	private List<Car> cars;

	// technical attributes
	private String userId;

	private UserType type;

	// gps co ordinates if avialable
	private String location;

	public User() {
		// This is for test
		/*this.contactNumber = "9867090753";
		this.emailaddress = "iyyani.jithin@capgemini.com";
		this.gender = "Male";
		this.address = new Address("604 Chamunda", "Navi Mumbai",
				"Maharashtra", "400708", new Country(UUID.randomUUID()
						.toString(), "India", "IN"));
		this.gender = "male";
		this.location = "3,4,5";
		this.name = "Jithin";
		this.passport = "J145234324";
		this.termsAndConditions = true;
		this.type = UserType.USER;
		this.userId = "1234144123";
		this.username = "jitsonfire";*/
		this.cars = new ArrayList<Car>();

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public boolean isTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(boolean termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

}
