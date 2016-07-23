package com.stolenvehicle.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.Query;
import com.stolenvehicle.dao.UserDao;
import com.stolenvehicle.entity.User;
import com.stolenvehicle.exception.BusinessException;

@Service
public class UserDaoImpl extends AbstractDao implements UserDao {

	private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	private static final class UserResultSetExtractor implements ResultSetExtractor<User> {

		@Override
		public User extractData(final ResultSet resultSet) throws SQLException {

			User user = null;
			if (resultSet.next()) {
				user = new User();

			}
			return user;
		}
	};

	@Override
	public User getUser(String emailAddress, String password) throws BusinessException {

		final Object userObject = this.fetch(Query.GET_USER_BY_EMAIL_ID, new Object[] { emailAddress, password },
				new UserResultSetExtractor());
		return (User) userObject;
	}

}
