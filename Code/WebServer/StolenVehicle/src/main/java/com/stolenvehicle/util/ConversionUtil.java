package com.stolenvehicle.util;

import com.stolenvehicle.dto.TheftInformationTo;
import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.dto.VehicleTo;
import com.stolenvehicle.entity.TheftInformation;
import com.stolenvehicle.entity.User;
import com.stolenvehicle.entity.Vehicle;

/*bad class we  need to improve this*/

public class ConversionUtil {

	public static User covertUserTo(UserTo userTo) {

		User user = new User();
		user.setAddress(userTo.getAddress());
		user.setCity(userTo.getCity());
		user.setContactNumber(userTo.getContactNumber());
		user.setCountry_id(userTo.getCountry_id());
		user.setEmail_notification(userTo.isEmail_notification());
		user.setEmailaddress(userTo.getEmailaddress());
		user.setGender(userTo.getGender());
		user.setIc_password(userTo.getIc_password());
		user.setName(userTo.getName());
		user.setPassword(userTo.getPassword());
		user.setTermsAndCondition(userTo.isTermsAndCondition());
		user.setUserStatus(userTo.getUserStatus());
		return user;

	}

	public static UserTo convertUserEntity(User userEntity) {

		UserTo user = new UserTo();
		user.setId(userEntity.getId());
		user.setAddress(userEntity.getAddress());
		user.setCity(userEntity.getCity());
		user.setContactNumber(userEntity.getContactNumber());
		// Think of this
		// user.setCountry_id(userTo.getCountry_id());
		user.setEmail_notification(userEntity.isEmail_notification());
		user.setEmailaddress(userEntity.getEmailaddress());
		user.setGender(userEntity.getGender());
		user.setIc_password(userEntity.getIc_password());
		user.setName(userEntity.getName());
		user.setPassword(userEntity.getPassword());
		user.setTermsAndCondition(userEntity.isTermsAndCondition());
		user.setUserStatus(userEntity.getUserStatus());
		return user;
	}

	public static Vehicle convertVehicleTo(VehicleTo vehicleTo) {

		Vehicle vehicle = new Vehicle();
		vehicle.setId(vehicleTo.getId());
		vehicle.setColor(vehicleTo.getColor());
		vehicle.setCountry_id(vehicleTo.getCountry_id());
		vehicle.setExtra_info(vehicleTo.getExtra_info());
		vehicle.setMake(vehicleTo.getMake());
		vehicle.setModel(vehicleTo.getMake());
		vehicle.setRegistrationNo(vehicleTo.getRegistrationNo());
		vehicle.setStolen(vehicleTo.isStolen());
		vehicle.setType(vehicleTo.getType());
		vehicle.setUser_id(vehicleTo.getUser_id());
		vehicle.setYear_of_make(vehicleTo.getYear_of_make());
		return vehicle;
	}

	public static VehicleTo convertVehicleEntity(Vehicle vehicle) {

		VehicleTo vehicleTo = new VehicleTo();
		vehicleTo.setId(vehicle.getId());
		vehicleTo.setColor(vehicle.getColor());
		vehicleTo.setCountry_id(vehicle.getCountry_id());
		vehicleTo.setExtra_info(vehicle.getExtra_info());
		vehicleTo.setMake(vehicle.getMake());
		vehicleTo.setModel(vehicle.getMake());
		vehicleTo.setRegistrationNo(vehicle.getRegistrationNo());
		vehicleTo.setStolen(vehicle.isStolen());
		vehicleTo.setType(vehicle.getType());
		vehicleTo.setUser_id(vehicle.getUser_id());
		vehicleTo.setYear_of_make(vehicle.getYear_of_make());
		return vehicleTo;
	}

	public static TheftInformation convertTheftInformationTo(
			TheftInformationTo theftInformationTo) {

		TheftInformation theftInformation = new TheftInformation();
		theftInformation.setAuditToken(null);
		theftInformation.setCountry_id(theftInformationTo.getCountry_id());
		theftInformation.setFind_information_id(theftInformationTo
				.getFind_information_id());
		theftInformation.setRewards(theftInformationTo.getRewards());
		theftInformation.setStatus(theftInformationTo.getStatus());
		theftInformation.setTheft_dateTime(AppUtil
				.convertStringToTimestamp(theftInformationTo
						.getTheft_dateTime()));
		theftInformation.setTheft_location_cordinates(theftInformationTo
				.getTheft_location_cordinates());
		theftInformation.setVehicle_id(theftInformationTo.getVehicle_id());
		return theftInformation;
	}

	public static TheftInformationTo convertTheftInformation(
			TheftInformation theftInformation) {

		TheftInformationTo theftInformationTo = new TheftInformationTo();
		theftInformationTo.setCountry_id(theftInformation.getCountry_id());
		theftInformationTo.setFind_information_id(theftInformation
				.getFind_information_id());
		theftInformationTo.setRewards(theftInformation.getRewards());
		theftInformationTo.setStatus(theftInformation.getStatus());
		theftInformationTo.setTheft_dateTime(theftInformation.getTheft_dateTime().toString());
		/*
		 * theftInformationTo.setTheft_dateTime(theftInformation
		 * .getTheft_dateTime().toLocaleString());
		 */theftInformationTo.setTheft_location_cordinates(theftInformation
				.getTheft_location_cordinates());
		theftInformationTo.setVehicle_id(theftInformation.getVehicle_id());
		theftInformationTo.setUser(ConversionUtil
				.convertUserEntity(theftInformation.getUser()));
		theftInformationTo.setVehicle(convertVehicleEntity(theftInformation
				.getVehicle()));
		return theftInformationTo;
	}
}
