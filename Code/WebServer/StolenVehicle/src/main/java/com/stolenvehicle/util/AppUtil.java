package com.stolenvehicle.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.stolenvehicle.constants.ExceptionConstants;

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
			formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			Date date = formatter.parse(str_date);
			java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

			return timeStampDate;
		} catch (ParseException e) {

			return null;
		}
	}
}
