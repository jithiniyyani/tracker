package com.stolenvehicle.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stolenvehicle.dao.AttachmentDao;
import com.stolenvehicle.dto.AttachmentTo;
import com.stolenvehicle.entity.Attachment;
import com.stolenvehicle.exception.BusinessException;
import com.stolenvehicle.service.AttachmentService;
import com.stolenvehicle.util.ConversionUtil;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	private static final Logger LOGGER = Logger
			.getLogger(AttachmentServiceImpl.class);

	@Autowired
	private AttachmentDao attachmentDao;

	// Improve this class
	@Override
	public List<AttachmentTo> saveAttachments(
			List<AttachmentTo> attachmentToList, String vehilceId,
			String findInformationId) throws BusinessException {

		// bad code improve this
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		for (AttachmentTo attachmentTo : attachmentToList) {
			attachmentList
					.add(ConversionUtil.convertAttachmentTo(attachmentTo));
		}
		List<Attachment> saveAttachmentList = attachmentDao.saveAttachmentList(
				attachmentList, vehilceId, findInformationId);
		attachmentToList = new ArrayList<AttachmentTo>();
		for (Attachment attachment : saveAttachmentList) {

			attachmentToList.add(ConversionUtil
					.convertAttachmentEntity(attachment));
		}
		return attachmentToList;
	}

}
