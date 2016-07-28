package com.stolenvehicle.dao;

import java.util.List;

import com.stolenvehicle.entity.FindInformation;
import com.stolenvehicle.exception.BusinessException;

public interface FindInformationDao extends GenericDao {

	public FindInformation saveFindInformation(FindInformation findInformation)
			throws BusinessException;

	public List<FindInformation> getFindInforamtionForUser(String user_id)
			throws BusinessException;
}
