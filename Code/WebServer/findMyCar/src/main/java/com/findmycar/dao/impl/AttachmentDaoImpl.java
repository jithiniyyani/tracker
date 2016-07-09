package com.findmycar.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import com.findmycar.dao.AttachmentDao;
import com.findmycar.to.Attachment;

@Service
public class AttachmentDaoImpl extends AbstractDao implements AttachmentDao {

	public static final String FIND_ATTACHMENT_BY_ID = "select * from attachments where id = ?";

	@Override
	public Attachment fetchAttachmentById(String imageId) {

		Attachment attachment = (Attachment) this.fetch(FIND_ATTACHMENT_BY_ID,
				new Object[] { imageId }, new CarImageResultSetExtractorImp());

		return attachment;
	}

	private static final class CarImageResultSetExtractorImp implements
			ResultSetExtractor<Attachment> {

		@Override
		public Attachment extractData(final ResultSet resultSet)
				throws SQLException {

			Attachment attachment = null;
			while (resultSet.next()) {
				attachment = new Attachment();
				attachment.setAttachmentName(resultSet
						.getString("attachment_name"));
				attachment.setAttachmentPath(resultSet
						.getString("attachment_path"));

			}
			return attachment;
		}
	};

}
