package com.stolenvehicle.dao;

import com.stolenvehicle.entity.FindInformation;
import com.stolenvehicle.exception.BusinessException;

public interface FindInformationDao extends GenericDao {

	public FindInformation saveFindInformation(FindInformation findInformation)
			throws BusinessException;
}
