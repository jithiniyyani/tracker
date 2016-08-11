package com.stolenvehicle.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.AttachmentTypeEnum;
import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.Query;
import com.stolenvehicle.constants.VehicleEnum;
import com.stolenvehicle.dao.VehicleDao;
import com.stolenvehicle.entity.Attachment;
import com.stolenvehicle.entity.Vehicle;
import com.stolenvehicle.exception.BusinessException;

@Service
public class VehicleDaoImpl extends AbstractDao implements VehicleDao {

	private static final Logger LOGGER = Logger.getLogger(VehicleDaoImpl.class);

	private static final class VehicleResultSetExtractor implements ResultSetExtractor<List<Vehicle>> {

		@Override
		public List<Vehicle> extractData(final ResultSet resultSet) throws SQLException {

			List<Vehicle> vehicles = new ArrayList<Vehicle>();
			while (resultSet.next()) {

				Vehicle vehicle = new Vehicle();
				vehicle.setId(resultSet.getString("id"));
				vehicle.setType(VehicleEnum.valueOf(resultSet.getString("type")));
				vehicle.setMake(resultSet.getString("make"));
				vehicle.setModel(resultSet.getString("model"));
				vehicle.setYear_of_make(resultSet.getString("year_of_make"));
				vehicle.setRegistrationNo(resultSet.getString("registrationNo"));
				vehicle.setColor(resultSet.getString("color"));
				vehicle.setExtra_info(resultSet.getString("extra_info"));
				vehicle.setStolen(resultSet.getString("stolen").equals("true") ? true : false);

				vehicles.add(vehicle);
			}
			return vehicles;
		}
	};

	private static final class VehicleTypeResultSetExtractor implements ResultSetExtractor<List<String>> {

		private String attributeName;

		public VehicleTypeResultSetExtractor(String attributeName) {
			this.attributeName = attributeName;
		}

		@Override
		public List<String> extractData(final ResultSet resultSet) throws SQLException {

			List<String> vehicleType = new ArrayList<String>();
			while (resultSet.next()) {

				vehicleType.add(resultSet.getString(attributeName));
			}
			return vehicleType;
		}
	};

	private static final class AttachmentResultSetExtractor implements ResultSetExtractor<List<Attachment>> {

		@Override
		public List<Attachment> extractData(final ResultSet resultSet) throws SQLException {

			List<Attachment> attachments = new ArrayList<Attachment>();
			while (resultSet.next()) {

				Attachment attachment = new Attachment();
				attachment.setId(resultSet.getString("id"));
				attachment.setAttachment_name(resultSet.getString("attachment_name"));
				attachment.setAttachment_path(resultSet.getString("attachment_path"));
				attachment.setAttachmentEnum(AttachmentTypeEnum.valueOf(resultSet.getString("attachment_type")));
				attachment.setVehicle_id(resultSet.getString("vehicle_id"));
				attachment.setPublicUrl(resultSet.getString("publicUrl"));
				attachment.setFind_information_id(resultSet.getString("find_information_id"));
				attachments.add(attachment);
			}
			return attachments;
		}
	};

	@Override
	public Vehicle saveVehicle(Vehicle vehicle) throws BusinessException {
		String id = UUID.randomUUID().toString();
		vehicle.setId(id);
		this.save(Query.SAVE_VEHICLE,
				new Object[] { vehicle.getId(), vehicle.getType().toString(), vehicle.getMake(), vehicle.getModel(),
						vehicle.getYear_of_make(), vehicle.getColor(), vehicle.getRegistrationNo(),
						vehicle.getExtra_info(), vehicle.isStolen() ? "true" : "false", vehicle.getUser_id(),
						vehicle.getCountry_id(), Constants.APP_NAME });
		return vehicle;
	}

	@Override
	public List<Vehicle> getRegisteredVehicleForUser(String userId) throws BusinessException {

		final Object vehicleListOjbect = this.fetch(Query.GET_VEHICLE_BY_USER_ID, new Object[] { userId },
				new VehicleResultSetExtractor());
		List<Vehicle> vehicleList = (List<Vehicle>) vehicleListOjbect;
		if (vehicleList != null) {

			for (Vehicle vehicle : vehicleList) {

				Object attachmentList = this.fetch(Query.GET_ATTACHMENTS_BY_VEHICLE_ID,
						new Object[] { vehicle.getId() }, new AttachmentResultSetExtractor());
				vehicle.setAttachments((List<Attachment>) attachmentList);
			}

		}
		return vehicleList;
	}

	@Override
	public List<String> getVehicleTypes(String countryId) throws BusinessException {

		final Object vehicleTypeListOjbect = this.fetch(Query.GET_VEHICLE_TYPE_LIST, new Object[] { countryId },
				new VehicleTypeResultSetExtractor("type"));
		return (List<String>) vehicleTypeListOjbect;
	}

	@Override
	public List<String> getVehicleMakeList(String countryId, String vehicleType) throws BusinessException {

		final Object vehicleMakeListOjbect = this.fetch(Query.GET_VEHICLE_MAKE_LIST,
				new Object[] { countryId, vehicleType }, new VehicleTypeResultSetExtractor("make"));
		return (List<String>) vehicleMakeListOjbect;
	}

	@Override
	public List<String> getVehicleModelList(String countryId, String vehicleType, String vehicleMake)
			throws BusinessException {

		final Object vehicleModelListOjbect = this.fetch(Query.GET_VEHICLE_MODEL_LIST,
				new Object[] { countryId, vehicleType, vehicleMake }, new VehicleTypeResultSetExtractor("model"));
		return (List<String>) vehicleModelListOjbect;
	}

}
