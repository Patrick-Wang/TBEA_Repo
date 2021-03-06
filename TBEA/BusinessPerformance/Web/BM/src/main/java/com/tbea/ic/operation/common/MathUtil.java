package com.tbea.ic.operation.common;

public class MathUtil {
	public static Double sum(Double[] vals){
		Double ret = null;
		for (int i = 0; i < vals.length; ++i){
			if (vals[i] != null){
				if (ret == null){
					ret = vals[i];
				}else{
					ret += vals[i];
				}
			}
		}
		return ret;
	}
	
	public static Double sum(Double val1, Double val2){
		Double ret = val1;
		if (val2 != null){
			if (ret != null){
				ret += val2;
			}else{
				ret = val2;
			}
		}
		return ret;
	}
	
	public static boolean isZero(Double val){
		return Math.abs(val) < 0.0000001;
	}
	
	public static boolean isPositive(Double val){
		return val > 0;
	}
	
	public static boolean isNegative(Double val){
		return val < 0;
	}
	
	public static Double division(Double sub, Double base) {
		if (base == null || sub == null){
			return null;
		}
		return sub / base;
	}

	public static Double mul(Double val1, Double val2) {
		if (val1 == null || val2 == null){
			return null;
		}
		return val2 * val1;
	}
	
	public static Double toDouble(String val) {
		try {
			return Double.valueOf(val);
		} catch (Exception e) {
		}
		return null;
	}
	
	public static String trimZero(String val){
		int index = val.lastIndexOf('.');
		if (index > 0){
			int len = val.length() - 1;
			while (len > index){
				if (val.charAt(len) != '0'){
					break;
				}
				--len;
			}

			if (len == index){
				val = val.substring(0, index);
			}else if (len < val.length() - 1){
				val = val.substring(0, len + 1);
			}
		}
		return val;
	}
	
	public static Double minus(Double val1, Double val2) {
		if (val1 == null || val2 == null){
			return null;
		}else {
			return val1 - val2;
		}
	}

}
