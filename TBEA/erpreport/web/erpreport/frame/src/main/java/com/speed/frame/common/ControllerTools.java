package com.speed.frame.common;

import javax.servlet.http.HttpServletRequest;

public class ControllerTools {
	public static boolean isAjaxRequest(HttpServletRequest httpRequest) {
		String requestType = httpRequest.getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}
}
