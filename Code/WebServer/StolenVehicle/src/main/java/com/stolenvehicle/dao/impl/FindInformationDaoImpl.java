package com.stolenvehicle.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.FindStatusEnum;
import com.stolenvehicle.constants.Query;
import com.stolenvehicle.dao.FindInformationDao;
import com.stolenvehicle.entity.FindInformation;
import com.stolenvehicle.exception.BusinessException;
import com.stolenvehicle.util.AppUtil;

@Service
public class FindInformationDaoImpl extends AbstractDao implements
		FindInformationDao {

	private static final class FindInformationResultSetExtractor implements
			ResultSetExtractor<List<FindInformation>> {

		@Override
		public List<FindInformation> extractData(final ResultSet resultSet)
				throws SQLException {

			List<FindInformation> findInformationList = new ArrayList<FindInformation>();
			FindInformation findInformation;
			if (resultSet.next()) {
				findInformation = new FindInformation();
				findInformationList.add(findInformation);

				findInformation.setId(resultSet.getString("fi.id"));
				findInformation.setLocators_name(resultSet
						.getString("fi.locators_name"));
				findInformation.setFind_location_cordinates(resultSet
						.getString("fi.find_location_cordinates"));
				findInformation
						.setLocators_contactNumber("fi.locators_contactNumber");

			}
			return findInformationList;
		}
	};

	@Override
	public FindInformation saveFindInformation(FindInformation findInformation)
			throws BusinessException {

		String id = UUID.randomUUID().toString();
		findInformation.setId(id);
		this.save(
				Query.SAVE_FIND_INFORMATION,
				new Object[] {
						findInformation.getId(),
						findInformation.getFind_location_cordinates(),
						AppUtil.convertStringToTimestamp(findInformation
								.getFind_dateTime()),
						findInformation.getLocators_name(),
						findInformation.getLocators_email(),
						findInformation.getLocators_contactNumber(),
						findInformation.getTheft_information_id(),
						FindStatusEnum.REPORTED.toString(), Constants.APP_NAME });
		return findInformation;
	}

	@Override
	public List<FindInformation> getFindInforamtionForUser(String user_id)
			throws BusinessException {

		final Object findInfoOjbectList = this.fetch(
				Query.GET_FIND_INFOLIST_BY_USER_ID, new Object[] { user_id },
				new FindInformationResultSetExtractor());
		return (List<FindInformation>) findInfoOjbectList;
	}
}
