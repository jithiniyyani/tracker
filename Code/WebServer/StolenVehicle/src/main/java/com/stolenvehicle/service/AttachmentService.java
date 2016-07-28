package com.stolenvehicle.service;

import java.util.List;

import com.stolenvehicle.dto.AttachmentTo;
import com.stolenvehicle.exception.BusinessException;

public interface AttachmentService {

	public List<AttachmentTo> saveAttachments(
			List<AttachmentTo> attachmentList, String vehilceId,
			String findInformationId) throws BusinessException;
}
