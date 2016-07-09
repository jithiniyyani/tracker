package com.findmycar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.findmycar.dao.CarDao;
import com.findmycar.exception.BusinessException;
import com.findmycar.to.Attachment;
import com.findmycar.to.Car;

@Service
public class CarDaoImpl extends AbstractDao implements CarDao {

	private static final String FIND_CAR_BY_USER_ID = "select * from car where user_id = ?";
	
	private static final String FIND_CAR_IMAGES_BY_CAR_ID = "select * from attachments where car_id = ?";

	private static final String DELETE_CAR_BY_ID = "delete from car where id = ?";

	private static final String UPDATE_CAR_AS_STOLEN = "update car set stolen = 'true' where id = ?";

	private static final String INSERT_CAR = "insert into car values(?,?,?,?,?,?,?,'system',now(),null,null);";
	private static final String INSERT_CAR_IMAGES = "insert into attachments values(?,?,?,?,'system',now(),null,null);";
	private static final String FIND_STOLEN_CARS_BY_REGISTRATION_NUMBER = "select * from car where stolen = 'true' and registrationNo like ?";

	private static final class CarListResultSetExtractorImp implements
			ResultSetExtractor<List<Car>> {

		@Override
		public List<Car> extractData(final ResultSet resultSet)
				throws SQLException {

			List<Car> carList = new ArrayList<Car>();
			Car car = null;
			while (resultSet.next()) {
				car = new Car();
				car.setCarId(resultSet.getString("id"));
				car.setColor(resultSet.getString("color"));
				car.setManufacturer(resultSet.getString("manufacturer"));
				car.setModel(resultSet.getString("model"));
				car.setRegistrationNo(resultSet.getString("registrationNo"));
				car.setStolen(resultSet.getString("stolen").equals("true") ? true
						: false);
				car.setRegistrationDate(resultSet.getDate("createdAt")
						.toLocalDate().toString());
				carList.add(car);

			}
			return carList;
		}
	};

	private static final class CarImageListResultSetExtractorImp implements
			ResultSetExtractor<List<String>> {

		@Override
		public List<String> extractData(final ResultSet resultSet)
				throws SQLException {

			List<String> attachmentIdList = new ArrayList<String>();
			while (resultSet.next()) {
				attachmentIdList.add(resultSet.getString("id"));
			}
			return attachmentIdList;
		}
	};

	public Car saveCar() {

		return null;
	}

	public boolean deleteCar(String carId) {

		boolean status = false;
		this.save(DELETE_CAR_BY_ID, new Object[] { carId });
		status = true;
		return status;
	}

	public List<Car> findCarsByUserId(String userId) {

		List<Car> carList = (List<Car>) this.fetch(FIND_CAR_BY_USER_ID,
				new Object[] { userId }, new CarListResultSetExtractorImp());
		for (Car car : carList) {
			// get the list of attachments
			List<String> attachmentIdList = (List<String>) this.fetch(
					FIND_CAR_IMAGES_BY_CAR_ID, new Object[] { car.getCarId() },
					new CarImageListResultSetExtractorImp());
			car.setImages(attachmentIdList);
		}
		return carList;
	}

	public Car fetchCarById(String cardId) {

		return null;
	}

	@Override
	public Car addCarForUser(Car car, String userId, List<Attachment> images) {

		String carId = UUID.randomUUID().toString();
		car.setCarId(carId);
		this.save(
				INSERT_CAR,
				new Object[] { carId, car.getRegistrationNo(),
						car.getManufacturer(), car.getModel(), car.getColor(),
						car.isStolen() ? "true" : "false", userId });

		for (Attachment attachment : images) {

			String uuid = UUID.randomUUID().toString();
			this.save(INSERT_CAR_IMAGES,
					new Object[] { uuid, attachment.getAttachmentName(),
							attachment.getAttachmentPath(), carId });
		}
		return car;

	}

	@Override
	public boolean markCarAsStolen(String carId) throws BusinessException {
		boolean status = false;
		this.save(UPDATE_CAR_AS_STOLEN, new Object[] { carId });
		status = true;
		return status;
	}

	@Override
	public List<Car> getStolenCars(String registrationNumber)
			throws BusinessException {

		List<Car> carList = (List<Car>) this.fetch(
				FIND_STOLEN_CARS_BY_REGISTRATION_NUMBER,
				new Object[] {  "%" + registrationNumber  + "%"},
				new CarListResultSetExtractorImp());
		for (Car car : carList) {
			// get the list of attachments
			List<String> attachmentIdList = (List<String>) this.fetch(
					FIND_CAR_IMAGES_BY_CAR_ID, new Object[] { car.getCarId() },
					new CarImageListResultSetExtractorImp());
			car.setImages(attachmentIdList);
		}
		return carList;
	}
}
