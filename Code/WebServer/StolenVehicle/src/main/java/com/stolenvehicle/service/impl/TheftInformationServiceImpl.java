package com.stolenvehicle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stolenvehicle.dao.TheftInformationDao;
import com.stolenvehicle.dto.TheftInformationTo;
import com.stolenvehicle.entity.TheftInformation;
import com.stolenvehicle.exception.BusinessException;
import com.stolenvehicle.service.TheftInformationService;
import com.stolenvehicle.util.ConversionUtil;

@Service
public class TheftInformationServiceImpl implements TheftInformationService {

	@Autowired
	private TheftInformationDao theftInformationDao;

	@Override
	public TheftInformationTo saveTheftInformation(
			TheftInformationTo theftInformationTo) throws BusinessException {

		TheftInformation theftInformation = ConversionUtil
				.convertTheftInformationTo(theftInformationTo);
		theftInformationDao.saveTheftInformation(theftInformation);
		theftInformationTo.setId(theftInformation.getId());
		return theftInformationTo;
	}

	@Override
	public TheftInformationTo getTheftInformation(String theftId)
			throws BusinessException {

		TheftInformation theftInformation = theftInformationDao
				.getTheftInformation(theftId);
		TheftInformationTo theftInformationTo = ConversionUtil
				.convertTheftInformation(theftInformation);
		return theftInformationTo;
	}

}
