package com.stolenvehicle.service;

import java.util.List;

import com.stolenvehicle.dto.FindInformationTo;
import com.stolenvehicle.exception.BusinessException;

public interface FindInformationService {

	public FindInformationTo saveFindInformation(
			FindInformationTo findInformationTo) throws BusinessException;

	public List<FindInformationTo> getFindInformationListForUser(String user_id)
			throws BusinessException;
}
