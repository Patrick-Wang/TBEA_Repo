package com.tbea.datatransfer.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonMethod {

	public static Double objectToDouble(Object inputObject) {
		Double output = null;
		try {
			if (null != inputObject) {
				output = Double.valueOf(String.valueOf(inputObject).replace(
						",", ""));
			} else {
				output = 0.0D;
			}
		} catch (Exception e) {
			output = 0.0D;
		}
		return output;
	}

	public static Integer objectToInteger(Object inputObject) {
		Integer output = null;
		try {
			if (null != inputObject) {
				output = Integer.valueOf(String.valueOf(inputObject).replace(
						",", ""));
			} else {
				output = 0;
			}
		} catch (Exception e) {
			output = 0;
		}
		return output;
	}

	public static Date objectToDate(SimpleDateFormat simpleDateFormat,
			Object inputObject) {
		Date output = null;
		try {
			if (null != inputObject) {
				output = simpleDateFormat.parse(String.valueOf(inputObject));
			}
		} catch (Exception e) {
			output = null;
		}
		return output;
	}

	public static String getPercent(Double first, Double second) {
		String result = null;
		Double zero = 0.0D;
		if (!zero.equals(second)) {
			result = (String.format("%.2f", first / second * 100) + "%");
		} else {
			result = "-";
		}
		return result;
	}
}
