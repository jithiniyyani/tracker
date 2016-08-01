package com.stolenvehicle.dto;

import com.stolenvehicle.constants.AttachmentTypeEnum;

public class AttachmentTo {

	private String id;

	private String attachment_name;

	private String attachment_path;

	private String publicUrl;

	private AttachmentTypeEnum attachmentTypeEnum;

	public AttachmentTo() {

	}

	public AttachmentTo(String id, String attachment_name,
			String attachment_path, String publicUrl,
			AttachmentTypeEnum attachmentTypeEnum) {
		this(attachment_name, attachment_path, publicUrl, attachmentTypeEnum);
		this.id = id;

	}

	public AttachmentTo(String attachment_name, String attachment_path,
			String publicUrl, AttachmentTypeEnum attachmentTypeEnum) {
		this.attachment_name = attachment_name;
		this.attachment_path = attachment_path;
		this.publicUrl = publicUrl;
		this.attachmentTypeEnum = attachmentTypeEnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAttachment_name() {
		return attachment_name;
	}

	public void setAttachment_name(String attachment_name) {
		this.attachment_name = attachment_name;
	}

	public String getAttachment_path() {
		return attachment_path;
	}

	public void setAttachment_path(String attachment_path) {
		this.attachment_path = attachment_path;
	}

	public AttachmentTypeEnum getAttachmentTypeEnum() {
		return attachmentTypeEnum;
	}

	public void setAttachmentTypeEnum(AttachmentTypeEnum attachmentTypeEnum) {
		this.attachmentTypeEnum = attachmentTypeEnum;
	}

	public String getPublicUrl() {
		return publicUrl;
	}

	public void setPublicUrl(String publicUrl) {
		this.publicUrl = publicUrl;
	}

}
