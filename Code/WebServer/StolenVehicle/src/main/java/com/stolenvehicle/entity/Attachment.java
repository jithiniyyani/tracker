package com.stolenvehicle.entity;

import com.stolenvehicle.constants.AttachmentTypeEnum;

public class Attachment {

	private String id;
	private String attachment_name;
	private String attachment_path;
	private String vehicle_id;
	private String find_information_id;
	private AttachmentTypeEnum attachmentEnum;
	private AuditToken auditToken;
	private String publicUrl;

	public Attachment() {

	}

	public Attachment(String attachment_name, String attachment_path,
			String publicUrl, AttachmentTypeEnum attachmentTypeEnum,
			String vehicle_id, String find_information_id) {
		this.attachment_name = attachment_name;
		this.attachment_path = attachment_path;
		this.publicUrl = publicUrl;
		this.attachmentEnum = attachmentTypeEnum;
		this.vehicle_id = vehicle_id;
		this.find_information_id = find_information_id;
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

	public String getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getFind_information_id() {
		return find_information_id;
	}

	public void setFind_information_id(String find_information_id) {
		this.find_information_id = find_information_id;
	}

	public AttachmentTypeEnum getAttachmentEnum() {
		return attachmentEnum;
	}

	public void setAttachmentEnum(AttachmentTypeEnum attachmentEnum) {
		this.attachmentEnum = attachmentEnum;
	}

	public AuditToken getAuditToken() {
		return auditToken;
	}

	public void setAuditToken(AuditToken auditToken) {
		this.auditToken = auditToken;
	}

	public String getPublicUrl() {
		return publicUrl;
	}

	public void setPublicUrl(String publicUrl) {
		this.publicUrl = publicUrl;
	}

}
