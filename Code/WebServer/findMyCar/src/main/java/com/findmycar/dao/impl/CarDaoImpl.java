package com.findmycar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.findmycar.dao.CarDao;
import com.findmycar.to.Car;

@Service
public class CarDaoImpl extends AbstractDao implements CarDao {

	private static final String FIND_CAR_BY_USER_ID = "select * from car where user_id = ?";

	private static final String DELETE_CAR_BY_ID = "delete from car where id = ?";

	private static final String INSERT_CAR = "insert into car values(?,?,?,?,?,?,?,'system',now(),null,null);";

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
				carList.add(car);

			}
			return carList;
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
		return carList;
	}

	public Car fetchCarById(String cardId) {

		return null;
	}

	@Override
	public Car addCarForUser(Car car, String userId) {

		String uuid = UUID.randomUUID().toString();
		car.setCarId(uuid);
		this.save(
				INSERT_CAR,
				new Object[] { uuid, car.getRegistrationNo(),
						car.getManufacturer(), car.getModel(), car.getColor(),
						car.isStolen() ? "true" : "false", userId });

		return car;

	}
}
