package com.tbea.ic.operation.common;

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
		if (null != second && !zero.equals(second) && null != first) {
			result = (String.format("%.2f", first / second * 100) + "%");
		} else {
			result = "-";
		}
		return result;
	}

	public static Integer intcat(Integer x, Integer y) {
		Integer result = null;
		StringBuffer sb = new StringBuffer();
		sb = sb.append(x);
		sb = sb.append(y);
		try {
			result = Integer.valueOf(sb.toString());
		} catch (NumberFormatException e) {
		}
		return result;
	}
	
	public static Double divideDouble(Double first, Double second) {
		Double result = 0.0D;
		Double zero = 0.0D;
		if (null != second && !zero.equals(second) && null != first) {
			result = first / second;
		}
		return result;
	}
}
