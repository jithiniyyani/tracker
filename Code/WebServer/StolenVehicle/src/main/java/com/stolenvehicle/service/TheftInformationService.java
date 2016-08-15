package com.stolenvehicle.service;

import java.util.List;

import com.stolenvehicle.constants.TheftStatus;
import com.stolenvehicle.dto.SearchTo;
import com.stolenvehicle.dto.TheftInformationTo;
import com.stolenvehicle.exception.BusinessException;

public interface TheftInformationService {

	public TheftInformationTo saveTheftInformation(
			TheftInformationTo theftInformation) throws BusinessException;

	public TheftInformationTo getTheftInformation(String theftId)
			throws BusinessException;

	public TheftInformationTo getTheftInformationByVehicleRegistrationNumber(
			String registrationNUmber) throws BusinessException;

	public List<TheftInformationTo> getTheftInformationBySearchTo(
			SearchTo searchTo) throws BusinessException;

	public boolean updateTheftInformation(String theftId, String findId,
			TheftStatus theftStatus) throws BusinessException;

}
