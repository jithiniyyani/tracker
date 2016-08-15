package com.stolenvehicle.util;

import java.util.ArrayList;
import java.util.List;

import com.stolenvehicle.dto.AttachmentTo;
import com.stolenvehicle.dto.CountryTo;
import com.stolenvehicle.dto.FindInformationTo;
import com.stolenvehicle.dto.TheftInformationTo;
import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.dto.VehicleTo;
import com.stolenvehicle.entity.Attachment;
import com.stolenvehicle.entity.Country;
import com.stolenvehicle.entity.FindInformation;
import com.stolenvehicle.entity.TheftInformation;
import com.stolenvehicle.entity.User;
import com.stolenvehicle.entity.Vehicle;

/*bad class we  need to improve this*/

public class ConversionUtil {

	public static User covertUserTo(UserTo userTo) {

		User user = new User();
		user.setId(userTo.getId());
		user.setAddress(userTo.getAddress());
		user.setAddressCordinates(userTo.getAddressCordinates());
		user.setCity(userTo.getCity());
		user.setContactNumber(userTo.getContactNumber());
		user.setCountry_id(userTo.getCountry_id());
		user.setEmail_notification(userTo.isEmail_notification());
		user.setEmailaddress(userTo.getEmailaddress());
		user.setGender(userTo.getGender());
		user.setIc_passport(userTo.getIc_passport());
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
		user.setAddressCordinates(userEntity.getAddressCordinates());
		user.setCity(userEntity.getCity());
		user.setContactNumber(userEntity.getContactNumber());
		user.setCountry_id(userEntity.getCountry_id());
		user.setEmail_notification(userEntity.isEmail_notification());
		user.setEmailaddress(userEntity.getEmailaddress());
		user.setGender(userEntity.getGender());
		user.setIc_passport(userEntity.getIc_passport());
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
		vehicle.setModel(vehicleTo.getModel());
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
		List<Attachment> attachments = vehicle.getAttachments();
		if (attachments != null) {

			List<AttachmentTo> attachmentToList = new ArrayList<AttachmentTo>();
			vehicleTo.setAttachments(attachmentToList);
			for (Attachment attachment : attachments) {

				attachmentToList
						.add(new AttachmentTo(attachment.getId(), attachment
								.getAttachment_name(), null, attachment
								.getPublicUrl(), attachment.getAttachmentEnum()));

			}
		}
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
		theftInformationTo.setId(theftInformation.getId());
		theftInformationTo.setCountry_id(theftInformation.getCountry_id());
		theftInformationTo.setFind_information_id(theftInformation
				.getFind_information_id());
		theftInformationTo.setRewards(theftInformation.getRewards());
		theftInformationTo.setStatus(theftInformation.getStatus());
		theftInformationTo.setTheft_dateTime(theftInformation
				.getTheft_dateTime().toString());
		theftInformationTo.setTheft_location_cordinates(theftInformation
				.getTheft_location_cordinates());
		theftInformationTo.setVehicle_id(theftInformation.getVehicle_id());
		theftInformationTo.setUser(ConversionUtil
				.convertUserEntity(theftInformation.getUser()));
		theftInformationTo.setVehicle(convertVehicleEntity(theftInformation
				.getVehicle()));
		return theftInformationTo;
	}

	public static Attachment convertAttachmentTo(AttachmentTo attachmentTo) {

		Attachment attachment = new Attachment();
		attachment.setAttachment_name(attachmentTo.getAttachment_name());
		attachment.setAttachment_path(attachmentTo.getAttachment_path());
		attachment.setPublicUrl(attachmentTo.getPublicUrl());
		attachment.setAttachmentEnum(attachmentTo.getAttachmentTypeEnum());
		return attachment;
	}

	public static AttachmentTo convertAttachmentEntity(Attachment attachment) {
		AttachmentTo attachmentTo = new AttachmentTo();
		attachmentTo.setAttachment_name(attachment.getAttachment_name());
		// attachmentTo.setAttachment_path(attachment.getAttachment_path());
		attachmentTo.setId(attachment.getId());
		attachmentTo.setPublicUrl(attachment.getPublicUrl());
		attachmentTo.setAttachmentTypeEnum(attachment.getAttachmentEnum());
		return attachmentTo;
	}

	public static FindInformation convertFindInformationTo(
			FindInformationTo findInformationTo) {

		FindInformation findInformation = new FindInformation();
		findInformation.setFind_dateTime(findInformationTo.getFind_dateTime());
		findInformation.setFind_location_cordinates(findInformationTo
				.getFind_location_cordinates());
		findInformation.setFindStatus(findInformationTo.getFindStatus());
		findInformation.setId(findInformationTo.getId());
		findInformation.setLocators_contactNumber(findInformationTo
				.getLocators_contactNumber());
		findInformation
				.setLocators_email(findInformationTo.getLocators_email());
		findInformation.setLocators_name(findInformationTo.getLocators_name());
		findInformation.setTheft_information_id(findInformationTo
				.getTheft_information_id());
		return findInformation;
	}

	public static List<FindInformationTo> convertFindInformationEntityList(
			List<FindInformation> findInfomtionEntityList) {

		List<FindInformationTo> findInformationToList = new ArrayList<>();
		for (FindInformation findInformation : findInfomtionEntityList) {

			FindInformationTo findInformationTo = new FindInformationTo(
					findInformation.getId(),
					findInformation.getLocators_name(),
					findInformation.getLocators_email(),
					findInformation.getLocators_contactNumber(),
					findInformation.getFind_location_cordinates());
			findInformationTo.setTheft_information_id(findInformation
					.getTheft_information_id());
			List<AttachmentTo> attachmentList = new ArrayList<>();
			findInformationTo.setAttachments(attachmentList);
			for (Attachment attachment : findInformation.getAttachments()) {

				attachmentList.add(ConversionUtil
						.convertAttachmentEntity(attachment));
			}
			findInformationToList.add(findInformationTo);

		}
		return findInformationToList;
	}

	public static List<CountryTo> convertCountryEntityList(
			List<Country> countryList) {

		List<CountryTo> countryToList = new ArrayList<>();
		for (Country country : countryList) {
			countryToList.add(new CountryTo(country.getCountry(), country
					.getCountry_code()));
		}
		return countryToList;
	}
}
