package com.stolenvehicle.service;

import com.stolenvehicle.dto.FindInformationTo;
import com.stolenvehicle.exception.BusinessException;

public interface FindInformationService {

	public FindInformationTo saveFindInformation(
			FindInformationTo findInformationTo) throws BusinessException;
}
