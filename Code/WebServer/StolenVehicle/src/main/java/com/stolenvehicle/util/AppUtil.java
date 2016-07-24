package com.stolenvehicle.util;

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
}
