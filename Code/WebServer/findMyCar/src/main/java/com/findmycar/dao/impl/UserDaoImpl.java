package com.findmycar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.findmycar.dao.UserDao;
import com.findmycar.to.User;

@Service
public class UserDaoImpl extends AbstractDao implements UserDao {

	// Improve this check futher
	private final static String CHECK_IF_USER_EXISTS = "select * from user where username = ? or name = ? or emailaddress = ?  or passport = ? or contactNumber = ?;";

	private final static String INSERT_NEW_USER = "insert into user values(?,?,?,?,?,?,?,?,?,'system',now(),null,null);";

	private final static String FIND_USER = "select * from user where username = ? or emailaddress = ?";

	public User createUser(User user) {

		String uuid = UUID.randomUUID().toString();
		user.setUserId(uuid);
		this.save(
				INSERT_NEW_USER,
				new Object[] { uuid, user.getName(), user.getUsername(),
						user.getPassword(), user.getEmailaddress(),
						user.getGender(), user.getPassport(),
						user.getContactNumber(),
						user.isTermsAndConditions() ? "true" : "false" });

		return user;
	}

	public User updateUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public User findUserById() {
		// TODO Auto-generated method stub
		return null;
	}

	private static final class CheckIfUserExists implements
			ResultSetExtractor<Boolean> {

		@Override
		public Boolean extractData(final ResultSet resultSet)
				throws SQLException {

			boolean status = false;
			if (resultSet.next()) {
				status = true;
			}
			return status;
		}
	};

	@Override
	public boolean checkIfUserExists(User user) {
		final Object userObject = this.fetch(
				CHECK_IF_USER_EXISTS,
				new Object[] { user.getUsername(), user.getName(),
						user.getEmailaddress(), user.getPassport(),
						user.getContactNumber() }, new CheckIfUserExists());
		Boolean status = (Boolean) userObject;
		return status;
	}

	private static final class UserResultSetExtractor implements
			ResultSetExtractor<User> {

		@Override
		public User extractData(final ResultSet resultSet) throws SQLException {

			User user = null;
			if (resultSet.next()) {
				user = new User();
				user.setUserId(resultSet.getString("id"));
				user.setName(resultSet.getString("name"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setEmailaddress(resultSet.getString("emailaddress"));
				user.setGender(resultSet.getString("gender"));
				user.setPassport(resultSet.getString("passport"));
				user.setContactNumber(resultSet.getString("contactNumber"));
				user.setTermsAndConditions(resultSet.getString(
						"termsAndConditions").equals("true") ? true : false);

			}
			return user;
		}
	};

	@Override
	public User findUserByIdOrEmailAddress(User user) {

		final Object userObject = this.fetch(FIND_USER,
				new Object[] { user.getUsername(),user.getEmailaddress()},
				new UserResultSetExtractor());

		return (User) userObject;
	}

}
