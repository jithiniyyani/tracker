package com.stolenvehicle.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.ExceptionConstants;
import com.stolenvehicle.dto.AttachmentTo;
import com.stolenvehicle.dto.UserTo;

public class AppUtil {

	public static boolean checkIfUserHasSession(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		if (session == null) {

			throw new IllegalArgumentException(
					ExceptionConstants.INVALID_SESSION);
		} else {
			return true;
		}
	}

	public static boolean invalidateSession(HttpServletRequest request) {

		boolean status = false;
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			status = true;
		}
		return status;
	}

	public static Timestamp convertStringToTimestamp(String str_date) {
		try {
			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			Date date = formatter.parse(str_date);
			java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
			return timeStampDate;
		} catch (ParseException e) {

			throw new IllegalArgumentException(ExceptionConstants.ILLEGAL_DATE,
					e);
		}
	}

	public static UserTo getUserFromSession(HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		return (UserTo) session.getAttribute(Constants.USER);
	}

	public static List<AttachmentTo> getAttachmentListForTheft(
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (List<AttachmentTo>) session.getAttribute(Constants.ATTACHMENTS);
	}

	// new Locale(Constants.US_LOCALE)
	public static ResourceBundle getResourceBundle(final Locale locale) {
		return ResourceBundle.getBundle("locale", locale, new UTF8Control());
	}
}
