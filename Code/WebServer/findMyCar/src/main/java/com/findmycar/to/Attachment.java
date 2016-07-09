package com.findmycar.to;

public class Attachment {

	private String attachmentName;

	private String attachmentPath;

	public Attachment() {

	}

	public Attachment(String attachmentName, String attachmentPath) {
		this.attachmentName = attachmentName;
		this.attachmentPath = attachmentPath;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

}
