package com.stolenvehicle.dao;

import com.stolenvehicle.constants.TheftStatus;
import com.stolenvehicle.entity.TheftInformation;
import com.stolenvehicle.exception.BusinessException;

public interface TheftInformationDao extends GenericDao {

	public TheftInformation saveTheftInformation(
			TheftInformation theftInformation) throws BusinessException;

	public TheftInformation getTheftInformation(String theftInformationId)
			throws BusinessException;

	public TheftInformation getTheftInformationByVehicleRegistrationNumber(
			String registrationNumber) throws BusinessException;

	public boolean updateTheftInformationStatus(String theftId,
			TheftStatus theftStatus) throws BusinessException;

}
