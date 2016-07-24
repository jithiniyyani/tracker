package com.stolenvehicle.service;

import com.stolenvehicle.constants.TheftStatus;
import com.stolenvehicle.dto.TheftInformationTo;
import com.stolenvehicle.exception.BusinessException;

public interface TheftInformationService {

	public TheftInformationTo saveTheftInformation(
			TheftInformationTo theftInformation) throws BusinessException;

	public TheftInformationTo getTheftInformation(String theftId)
			throws BusinessException;

	public TheftInformationTo getTheftInformationByVehicleRegistrationNumber(
			String registrationNUmber) throws BusinessException;

	public boolean updateTheftInformation(String theftId,TheftStatus theftStatus)
			throws BusinessException;

}
