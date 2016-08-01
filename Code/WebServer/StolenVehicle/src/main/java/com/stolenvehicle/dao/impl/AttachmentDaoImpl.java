package com.stolenvehicle.dao.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.Query;
import com.stolenvehicle.dao.AttachmentDao;
import com.stolenvehicle.entity.Attachment;
import com.stolenvehicle.exception.BusinessException;

@Service
public class AttachmentDaoImpl extends AbstractDao implements AttachmentDao {

	@Override
	public List<Attachment> saveAttachmentList(List<Attachment> attachmentList,
			String vehilceId, String findInformationId)
			throws BusinessException {

		String attachmendId = UUID.randomUUID().toString();
		for (Attachment attachment : attachmentList) {
			attachmendId = UUID.randomUUID().toString();
			this.save(
					Query.SAVE_ATTACHMENT,
					new Object[] { attachmendId,
							attachment.getAttachment_name(),
							attachment.getAttachment_path(),attachment.getPublicUrl(), vehilceId,
							findInformationId,
							attachment.getAttachmentEnum().toString(),
							Constants.APP_NAME });
			attachment.setId(attachmendId);
		}
		return attachmentList;
	}

}
