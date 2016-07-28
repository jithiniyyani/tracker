package com.stolenvehicle.entity;

import com.stolenvehicle.constants.GenderEnum;
import com.stolenvehicle.constants.UserStatusEnum;

public class User {

	private String id;
	private String name;
	private String emailaddress;
	private String password;
	private GenderEnum gender;
	private String ic_password;
	private String contactNumber;
	private String city;
	private String address;
	private String activation_id;
	private UserStatusEnum userStatus;
	private boolean email_notification;
	private boolean termsAndCondition;
	private String country_id;
	private AuditToken auditToke;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		
		this.name = name;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public String getIc_password() {
		return ic_password;
	}

	public void setIc_password(String ic_password) {
		this.ic_password = ic_password;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserStatusEnum getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatusEnum userStatus) {
		this.userStatus = userStatus;
	}

	public boolean isEmail_notification() {
		return email_notification;
	}

	public void setEmail_notification(boolean email_notification) {
		this.email_notification = email_notification;
	}

	public boolean isTermsAndCondition() {
		return termsAndCondition;
	}

	public void setTermsAndCondition(boolean termsAndCondition) {
		this.termsAndCondition = termsAndCondition;
	}

	public String getCountry_id() {
		return country_id;
	}

	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}

	public AuditToken getAuditToke() {
		return auditToke;
	}

	public void setAuditToke(AuditToken auditToke) {
		this.auditToke = auditToke;
	}

	public String getActivation_id() {
		return activation_id;
	}

	public void setActivation_id(String activation_id) {
		this.activation_id = activation_id;
	}
	
}
