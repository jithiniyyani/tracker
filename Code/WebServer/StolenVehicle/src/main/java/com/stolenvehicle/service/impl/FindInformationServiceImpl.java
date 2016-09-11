package com.stolenvehicle.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.AttachmentTypeEnum;
import com.stolenvehicle.constants.FindStatusEnum;
import com.stolenvehicle.dao.AttachmentDao;
import com.stolenvehicle.dao.FindInformationDao;
import com.stolenvehicle.dto.AttachmentTo;
import com.stolenvehicle.dto.FindInformationTo;
import com.stolenvehicle.entity.Attachment;
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
	public FindInformationTo saveFindInformation(FindInformationTo findInformationTo) throws BusinessException {

		FindInformation findInformation = ConversionUtil.convertFindInformationTo(findInformationTo);
		findInformationDao.saveFindInformation(findInformation);
		List<AttachmentTo> attachmentToList = findInformationTo.getAttachments();
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		for (AttachmentTo attachmentTo : attachmentToList) {

			attachmentList.add(new Attachment(attachmentTo.getAttachment_name(), attachmentTo.getAttachment_path(),
					attachmentTo.getPublicUrl(), AttachmentTypeEnum.FIND, findInformationTo.getVehicle_id(),
					findInformationTo.getId()));

		}
		attachmentDao.saveAttachmentList(attachmentList, findInformationTo.getVehicle_id(), findInformation.getId());
		findInformationTo.setId(findInformation.getId());
		return findInformationTo;
	}

	@Override
	public List<FindInformationTo> getFindInformationListForUser(String user_id) throws BusinessException {

		List<FindInformation> findInforamtionForUser = findInformationDao.getFindInforamtionForUser(user_id);
		return ConversionUtil.convertFindInformationEntityList(findInforamtionForUser);

	}

	@Override
	public boolean updateFindInformationStatus(String find_id, FindStatusEnum findStatus) throws BusinessException {

		return findInformationDao.updateFindInformatoinStatus(find_id, findStatus);
	}

	@Override
	public List<FindInformationTo> findInformationListReadyForReward(String user_id) throws BusinessException {
		List<FindInformation> findInforamtionForUser = findInformationDao.findInformationListReadyForReward(user_id);
		return ConversionUtil.convertFindInformationEntityList(findInforamtionForUser);
	}

}
