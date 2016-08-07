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
import com.stolenvehicle.constants.ExceptionConstants;
import com.stolenvehicle.constants.Query;
import com.stolenvehicle.constants.TheftStatus;
import com.stolenvehicle.constants.VehicleEnum;
import com.stolenvehicle.dao.TheftInformationDao;
import com.stolenvehicle.entity.Attachment;
import com.stolenvehicle.entity.TheftInformation;
import com.stolenvehicle.entity.User;
import com.stolenvehicle.entity.Vehicle;
import com.stolenvehicle.exception.BusinessException;

@Service
public class TheftInformationDaiImpl extends AbstractDao implements
		TheftInformationDao {

	private static final class TheftInformationResultSetExtractor implements
			ResultSetExtractor<TheftInformation> {

		@Override
		public TheftInformation extractData(final ResultSet resultSet)
				throws SQLException {

			// ti.id,u.name,
			// v.registrationNo,ti.theft_dateTime,ti.theft_location_cordinates,v.type,v.make,v.model,v.year_of_make
			TheftInformation theftInformation = null;
			if (resultSet.next()) {
				theftInformation = new TheftInformation();
				theftInformation.setId(resultSet.getString("ti.id"));
				theftInformation.setTheft_dateTime(resultSet
						.getTimestamp("ti.theft_dateTime"));
				theftInformation.setTheft_location_cordinates(resultSet
						.getString("ti.theft_location_cordinates"));

				theftInformation.setStatus(TheftStatus.valueOf(resultSet
						.getString("ti.status")));
				User user = new User();
				user.setName(resultSet.getString("u.name"));
				user.setId(resultSet.getString("u.id"));
				theftInformation.setUser(user);
				Vehicle vehicle = new Vehicle();
				vehicle.setType(VehicleEnum.valueOf(resultSet
						.getString("v.type")));
				vehicle.setId(resultSet.getString("v.id"));
				vehicle.setModel(resultSet.getString("v.model"));
				vehicle.setYear_of_make((resultSet.getString("v.year_of_make")));
				vehicle.setRegistrationNo(resultSet
						.getString("v.registrationNo"));
				theftInformation.setVehicle(vehicle);

			}
			return theftInformation;
		}
	};

	private static final class TheftInformationAttachmentsResultSetExtractor
			implements ResultSetExtractor<List<Attachment>> {

		@Override
		public List<Attachment> extractData(final ResultSet resultSet)
				throws SQLException {

			List<Attachment> attachments = new ArrayList<Attachment>();
			while (resultSet.next()) {

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
	public TheftInformation saveTheftInformation(
			TheftInformation theftInformation) throws BusinessException {

		String id = UUID.randomUUID().toString();
		theftInformation.setId(id);
		this.save(
				Query.SAVE_THEFT,
				new Object[] { theftInformation.getId(),
						theftInformation.getTheft_location_cordinates(),
						theftInformation.getVehicle_id(),
						theftInformation.getCountry_id(),
						theftInformation.getRewards(),
						theftInformation.getTheft_dateTime(),
						theftInformation.getStatus().toString(), null,
						Constants.APP_NAME });
		return theftInformation;
	}

	@Override
	public TheftInformation getTheftInformation(String theftInformationId)
			throws BusinessException {

		final Object theftInfoOjbect = this.fetch(Query.GET_THEFT_INFO_BY_ID,
				new Object[] { theftInformationId },
				new TheftInformationResultSetExtractor());
		TheftInformation theftInformation = (TheftInformation) theftInfoOjbect;
		if (theftInformation == null) {

			throw new BusinessException(ExceptionConstants.THEFT_INFO_NOT_FOUND);
		} else {

			String vehicle_id = theftInformation.getVehicle().getId();
			final Object attachmentList = this.fetch(
					Query.GET_VEHICLE_ATTACHMENTS, new Object[] { vehicle_id },
					new TheftInformationAttachmentsResultSetExtractor());
			theftInformation.getVehicle().setAttachments(
					(List<Attachment>) attachmentList);
		}

		return theftInformation;

	}

	@Override
	public TheftInformation getTheftInformationByVehicleRegistrationNumber(
			String registrationNumber) throws BusinessException {

		final Object theftInfoOjbect = this.fetch(
				Query.GET_THEFT_INFO_BY_REGISTRATION_NUMBER, new Object[] { "%"
						+ registrationNumber + "%" },
				new TheftInformationResultSetExtractor());
		TheftInformation theftInformation = (TheftInformation) theftInfoOjbect;
		if (theftInformation == null) {

			throw new BusinessException(ExceptionConstants.THEFT_INFO_NOT_FOUND);
		} else {

			String vehicle_id = theftInformation.getVehicle().getId();
			final Object attachmentList = this.fetch(
					Query.GET_VEHICLE_ATTACHMENTS, new Object[] { vehicle_id },
					new TheftInformationAttachmentsResultSetExtractor());
			theftInformation.getVehicle().setAttachments(
					(List<Attachment>) attachmentList);
		}
		return theftInformation;
	}

	@Override
	public boolean updateTheftInformationStatus(String theftId, String findId,
			TheftStatus theftStatus) throws BusinessException {

		int count = this.save(Query.UPDATE_THEFT_INFO_STATUS, new Object[] {
				theftStatus.toString(), findId, theftId });
		return count > 0 ? true : false;
	}

}
