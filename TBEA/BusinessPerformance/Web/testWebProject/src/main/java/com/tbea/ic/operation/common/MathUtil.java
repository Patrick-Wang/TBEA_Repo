package com.tbea.ic.operation.common;

import java.util.List;
import java.util.Set;

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
	
	public static Double sum(List<Double> vals){
		Double ret = null;
		for (int i = 0; i < vals.size(); ++i){
			if (vals.get(i) != null){
				if (ret == null){
					ret = vals.get(i);
				}else{
					ret += vals.get(i);
				}
			}
		}
		return ret;
	}
	
	public static Integer sum(Integer[] vals){
		Integer ret = null;
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
		
		if (isZero(base)){
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
	
	public static Integer toInteger(String val) {
		try {
			return Integer.valueOf(val);
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

	public static Double o2d(Object val){
		if (val instanceof Integer){
			return ((Integer)val).doubleValue();
		}else if (val instanceof Double){
			return (Double) val;
		}else if (val instanceof String){
			return toDouble((String)val);
		}
		return null;
	}
	
	public static Double division(Integer sub, Integer base) {
		if (base == null || sub == null){
			return null;
		}
		if (0 == base){
			return null;
		}
		
		return sub.doubleValue() / base.doubleValue();
	}

	public static Integer toInteger(Double val) {
		if (null == val){
			return null;
		}
		return val.intValue();
	}

	public static Double toDouble(Integer val) {
		if (null == val){
			return null;
		}
		return val.doubleValue();
	}

	public static Integer minus(Integer val1, Integer val2) {
		if (val1 == null || val2 == null){
			return null;
		}else {
			return val1 - val2;
		}
	}

	public static Integer sum(Integer val1, Integer val2) {
		Integer ret = val1;
		if (val2 != null){
			if (ret != null){
				ret += val2;
			}else{
				ret = val2;
			}
		}
		return ret;
	}

	public static int max(List<Comparable> compares, Set<Integer> excludes) {
		if (!compares.isEmpty()){
			int ret = -1;
			for (int i = 0;i < compares.size(); ++i){
				if (excludes == null || !excludes.contains(i)){
					ret = i;
					break;
				}
			}
			if (ret >= 0){
				for (int i = ret + 1; i < compares.size(); ++i){
					if (excludes == null || !excludes.contains(i)){
						if (null != compares.get(i)){
							if (compares.get(ret) == null || 
								compares.get(i).compareTo(compares.get(ret)) > 0){
								ret = i;
							}
						}
					}
				}
			}
			return ret;
		}
		return -1;
	}
	
	public static int min(List<Comparable> compares, Set<Integer> excludes) {
		if (!compares.isEmpty()){
			int ret = -1;
			for (int i = 0;i < compares.size(); ++i){
				if (excludes == null || !excludes.contains(i)){
					ret = i;
					break;
				}
			}
			
			if (ret >= 0 && compares.get(ret) != null){
				for (int i = ret + 1; i < compares.size(); ++i){
					if (excludes == null || !excludes.contains(i)){
						if (null != compares.get(i)){
							if (compares.get(i).compareTo(compares.get(ret)) < 0){
								ret = i;
							}
						}else{
							ret = i;
							break;
						}
					}
				}
			}
			return ret;
		}
		return -1;
	}

}
