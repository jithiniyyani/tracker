package com.stolenvehicle.dao;

import java.util.List;

import com.stolenvehicle.constants.TheftStatus;
import com.stolenvehicle.dto.SearchTo;
import com.stolenvehicle.entity.TheftInformation;
import com.stolenvehicle.exception.BusinessException;

public interface TheftInformationDao extends GenericDao {

	public TheftInformation saveTheftInformation(TheftInformation theftInformation) throws BusinessException;

	public TheftInformation getTheftInformation(String theftInformationId) throws BusinessException;

	public TheftInformation getTheftInformationByVehicleRegistrationNumber(String registrationNumber)
			throws BusinessException;

	public boolean updateTheftInformationStatus(String theftId, String findId, TheftStatus theftStatus)
			throws BusinessException;

	public List<TheftInformation> getTheftInformationBySearchTo(SearchTo searchTo) throws BusinessException;

}
