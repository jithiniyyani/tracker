package com.stolenvehicle.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.GenderEnum;
import com.stolenvehicle.constants.Query;
import com.stolenvehicle.constants.UserStatusEnum;
import com.stolenvehicle.dao.UserDao;
import com.stolenvehicle.entity.AuditToken;
import com.stolenvehicle.entity.User;
import com.stolenvehicle.exception.BusinessException;

@Service
public class UserDaoImpl extends AbstractDao implements UserDao {

	private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	private static final class UserResultSetExtractor implements
			ResultSetExtractor<User> {

		@Override
		public User extractData(final ResultSet resultSet) throws SQLException {

			User user = null;
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getString("id"));
				user.setName(resultSet.getString("name"));
				user.setEmailaddress(resultSet.getString("emailaddress"));
				user.setPassword(resultSet.getString("password"));
				user.setGender(GenderEnum.valueOf(resultSet.getString("gender")));
				user.setIc_password(resultSet.getString("ic_passport"));
				user.setContactNumber(resultSet.getString("contactNumber"));
				user.setCity(resultSet.getString("city"));
				user.setAddress(resultSet.getString("address"));
				user.setUserStatus(UserStatusEnum.valueOf(resultSet
						.getString("status")));
				user.setEmail_notification(resultSet.getString(
						"email_notification").equals("true") ? true : false);
				user.setTermsAndCondition(resultSet.getString(
						"termsAndCondition").equals("true") ? true : false);
				user.setCountry_id(resultSet.getString("country_id"));
				user.setAuditToke(AuditToken.buildAuditTokenFromRs(resultSet));

			}
			return user;
		}
	};

	@Override
	public User getUser(String emailAddress, String password)
			throws BusinessException {

		final Object userObject = this.fetch(Query.GET_USER_BY_EMAIL_ID,
				new Object[] { emailAddress, password },
				new UserResultSetExtractor());
		User user = (User) userObject;
		if (user == null) {

			LOGGER.error("User not found for " + emailAddress + " " + password);
			throw new BusinessException(Constants.USER_NOT_FOUND);
		} else {

			return user;
		}
	}

	@Override
	public User saveUser(User user) {

		String userId = UUID.randomUUID().toString();
		user.setId(userId);
		this.save(
				Query.SAVE_USER,
				new Object[] { user.getId(), user.getName(),
						user.getEmailaddress(), user.getPassword(),
						user.getGender(), user.getIc_password(),
						user.getContactNumber(), user.getCity(),
						user.getAddress(), user.getUserStatus(),
						user.isEmail_notification(),
						user.isTermsAndCondition(), "1", Constants.APP_NAME });
		return user;
	}

}
