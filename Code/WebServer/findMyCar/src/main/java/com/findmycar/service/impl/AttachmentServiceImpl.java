package com.findmycar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findmycar.dao.AttachmentDao;
import com.findmycar.service.AttachmentService;
import com.findmycar.to.Attachment;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public Attachment fetchAttachmentById(String attachmentId) {

		Attachment attachment = attachmentDao.fetchAttachmentById(attachmentId);
		return attachment;
	}

}
