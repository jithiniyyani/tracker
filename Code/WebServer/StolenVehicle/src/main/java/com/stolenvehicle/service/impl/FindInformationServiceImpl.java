package com.stolenvehicle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stolenvehicle.dao.AttachmentDao;
import com.stolenvehicle.dao.FindInformationDao;
import com.stolenvehicle.dto.FindInformationTo;
import com.stolenvehicle.entity.FindInformation;
import com.stolenvehicle.exception.BusinessException;
import com.stolenvehicle.service.FindInformationService;
import com.stolenvehicle.util.ConversionUtil;

@Service
public class FindInformationServiceImpl implements FindInformationService {

	@Autowired
	private FindInformationDao findInformationDao;

	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public FindInformationTo saveFindInformation(
			FindInformationTo findInformationTo) throws BusinessException {

		FindInformation findInformation = ConversionUtil
				.convertFindInformationTo(findInformationTo);
		findInformationDao.saveFindInformation(findInformation);
		attachmentDao.saveAttachmentList(null,
				findInformationTo.getVehicle_id(), findInformation.getId());
		findInformationTo.setId(findInformation.getId());
		// also send user notification here
		return findInformationTo;
	}

}
