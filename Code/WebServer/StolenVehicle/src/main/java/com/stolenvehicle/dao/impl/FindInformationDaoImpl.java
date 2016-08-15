package com.stolenvehicle.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.AttachmentTypeEnum;
import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.FindStatusEnum;
import com.stolenvehicle.constants.Query;
import com.stolenvehicle.dao.FindInformationDao;
import com.stolenvehicle.entity.Attachment;
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
			while (resultSet.next()) {
				findInformation = new FindInformation();
				findInformationList.add(findInformation);
				findInformation.setId(resultSet.getString("fi.id"));
				findInformation.setTheft_information_id(resultSet
						.getString("ti.id"));
				findInformation.setLocators_name(resultSet
						.getString("fi.locators_name"));
				findInformation.setFind_location_cordinates(resultSet
						.getString("fi.find_location_cordinates"));
				findInformation.setLocators_contactNumber(resultSet
						.getString("fi.locators_contactNumber"));
				findInformation.setLocators_email(resultSet
						.getString("fi.locators_email"));
				findInformation.setVehilce_id(resultSet.getString("v.id"));

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

	private static final class FindInformationAttachmentsResultSetExtractor
			implements ResultSetExtractor<List<Attachment>> {

		@Override
		public List<Attachment> extractData(final ResultSet resultSet)
				throws SQLException {

			List<Attachment> attachments = new ArrayList<Attachment>();
			if (resultSet.next()) {

				Attachment attachment = new Attachment();
				attachment.setId(resultSet.getString("id"));
				attachment.setAttachment_name(resultSet
						.getString("attachment_name"));
				attachment.setAttachment_path(resultSet
						.getString("attachment_path"));
				attachment.setAttachmentEnum(AttachmentTypeEnum
						.valueOf(resultSet.getString("attachment_type")));
				attachment.setVehicle_id(resultSet.getString("vehicle_id"));
				attachment.setPublicUrl(resultSet.getString("publicUrl"));
				attachment.setFind_information_id(resultSet
						.getString("find_information_id"));
				attachments.add(attachment);
			}
			return attachments;
		}
	};

	@Override
	public List<FindInformation> getFindInforamtionForUser(String user_id)
			throws BusinessException {

		final Object findInfoOjbectList = this.fetch(
				Query.GET_FIND_INFOLIST_BY_USER_ID, new Object[] { user_id },
				new FindInformationResultSetExtractor());

		List<FindInformation> findInformationList = (List<FindInformation>) findInfoOjbectList;

		for (FindInformation findInformation : findInformationList) {

			final Object attachmentList = this.fetch(
					Query.GET_VEHICLE_ATTACHMENTS_FOR_FIND,
					new Object[] { findInformation.getVehilce_id(),
							findInformation.getId() },
					new FindInformationAttachmentsResultSetExtractor());

			List<Attachment> attachments = (List<Attachment>) attachmentList;
			findInformation.setAttachments(attachments);

		}

		return findInformationList;
	}

	@Override
	public boolean updateFindInformatoinStatus(String find_id,
			FindStatusEnum findStatus) throws BusinessException {
		int save = this.save(Query.UPDATE_FIND_INFORMATION_STATUS_BY_ID,
				new Object[] { findStatus.toString(), find_id, });
		return save > 0 ? true : false;
	}

	@Override
	public List<FindInformation> findInformationListReadyForReward(
			String user_id) throws BusinessException {
		final Object findInfoOjbectList = this.fetch(
				Query.GET_FIND_INFOLIST_BY_USER_ID_FOR_REWARD,
				new Object[] { user_id },
				new FindInformationResultSetExtractor());

		List<FindInformation> findInformationList = (List<FindInformation>) findInfoOjbectList;

		for (FindInformation findInformation : findInformationList) {

			final Object attachmentList = this.fetch(
					Query.GET_VEHICLE_ATTACHMENTS_FOR_FIND,
					new Object[] { findInformation.getVehilce_id(),
							findInformation.getId() },
					new FindInformationAttachmentsResultSetExtractor());

			List<Attachment> attachments = (List<Attachment>) attachmentList;
			findInformation.setAttachments(attachments);

		}

		return findInformationList;
	}
}
