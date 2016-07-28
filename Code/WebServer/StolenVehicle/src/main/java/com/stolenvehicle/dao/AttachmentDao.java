package com.stolenvehicle.dao;

import java.util.List;

import com.stolenvehicle.entity.Attachment;
import com.stolenvehicle.exception.BusinessException;

public interface AttachmentDao extends GenericDao {

	public List<Attachment> saveAttachmentList(List<Attachment> attachmentList,
			String vehilceId, String findInformationId)
			throws BusinessException;

}
