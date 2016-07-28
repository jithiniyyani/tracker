package com.stolenvehicle.dao.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.FindStatusEnum;
import com.stolenvehicle.constants.Query;
import com.stolenvehicle.dao.FindInformationDao;
import com.stolenvehicle.entity.FindInformation;
import com.stolenvehicle.exception.BusinessException;

@Service
public class FindInformationDaoImpl extends AbstractDao implements
		FindInformationDao {

	@Override
	public FindInformation saveFindInformation(FindInformation findInformation)
			throws BusinessException {

		String id = UUID.randomUUID().toString();
		findInformation.setId(id);
		this.save(
				Query.SAVE_FIND_INFORMATION,
				new Object[] { findInformation.getId(),
						findInformation.getFind_location_cordinates(),
						findInformation.getFind_dateTime(),
						findInformation.getLocators_name(),
						findInformation.getLocators_email(),
						findInformation.getLocators_contactNumber(),
						findInformation.getTheft_information_id(),
						FindStatusEnum.REPORTED.toString(), Constants.APP_NAME });
		return findInformation;
	}
}
