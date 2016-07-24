package com.stolenvehicle.util;

import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.entity.User;

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
}
