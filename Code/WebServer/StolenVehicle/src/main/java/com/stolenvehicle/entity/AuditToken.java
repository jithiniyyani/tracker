package com.stolenvehicle.entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AuditToken {

	private String createdBy;
	private Timestamp createdAt;
	private String modifiedBy;
	private String modifiedAt;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public static AuditToken buildAuditTokenFromRs(ResultSet rs)
			throws SQLException {
		// clean this up latter
		AuditToken auditToken = new AuditToken();
		auditToken.setCreatedAt(rs.getTimestamp("createdAt"));
		auditToken.setCreatedBy(rs.getString("createdBy"));
		// TO be used some time latter
		/*
		 * auditToken.setModifiedAt(modifiedAt);
		 * auditToken.setModifiedBy(modifiedBy);
		 */
		return auditToken;

	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}
