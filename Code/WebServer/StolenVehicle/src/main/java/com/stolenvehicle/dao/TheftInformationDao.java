package com.stolenvehicle.dao;

import com.stolenvehicle.entity.TheftInformation;
import com.stolenvehicle.exception.BusinessException;

public interface TheftInformationDao extends GenericDao {

	public TheftInformation saveTheftInformation(
			TheftInformation theftInformation) throws BusinessException;

	public TheftInformation getTheftInformation(String theftInformationId)
			throws BusinessException;

}
