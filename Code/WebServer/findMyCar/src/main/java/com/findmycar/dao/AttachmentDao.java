package com.findmycar.dao;

import com.findmycar.to.Attachment;

public interface AttachmentDao extends GenericDao {

	public Attachment fetchAttachmentById(String imageId);
}
