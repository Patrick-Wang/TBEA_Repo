package com.tbea.ic.operation.service.report.work;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.util.tools.MathUtil;
import com.xml.frame.report.util.EasyCalendar;

public class WorkReportUtil {
	public String ratioText(String ratio) {
		if (ratio == null || "--".equals(ratio)) {
			return "--";
		}
		if (ratio.startsWith("-")) {
			return "降低" + ratio.substring(1);
		}
		return "增长" + ratio;		
	}

	private String getMonthText(Calendar cal) {
		return (cal.get(Calendar.MONTH) + 1) + "月";
	}
	
	public String monthText(EasyCalendar ec, List<Integer> months) {
		Date d = ec.getDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.MONTH, months.get(0));
		String ret = getMonthText(cal);	
		for (int i = 1; i < months.size(); ++i) {
			cal.setTime(d);
			cal.add(Calendar.MONTH, months.get(i));
			ret += "," + getMonthText(cal);	
		}
		return ret;
	}
	
	public String minus(String f, String s) {
		Double df = MathUtil.o2d(f);
		Double ds = MathUtil.o2d(s);
		Double ret = MathUtil.minus(df, ds);
		if (ret != null) {
			BigDecimal b = new BigDecimal(ret);
			String sRet = b.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString();
			return sRet;
		}
		return "--";
	}
	
	public String absMinus(String f, String s) {
		Double df = MathUtil.o2d(f);
		Double ds = MathUtil.o2d(s);
		Double ret = MathUtil.minus(df, ds);
		if (ret != null) {
			BigDecimal b = new BigDecimal(Math.abs(ret));
			String sRet = b.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString();
			return sRet;
		}
		return "--";
	}
	
	public String avg(List<String> l) {
		List<Double> tmp = new ArrayList<Double>();
		for (String s : l) {
			Double dTmp = MathUtil.o2d(s);
			if (null != dTmp) {
				tmp.add(dTmp);
			}
		}
		Double dSum = MathUtil.sum(tmp);
		if (null != dSum) {
			BigDecimal b = new BigDecimal(dSum / tmp.size());
			String sRet = b.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString();
			return sRet;
		}
		return "--";
	}
	
	public boolean isAsc(List list) {
		Double preVal = MathUtil.o2d(list.get(0));
		Double nextVal = null;
		for (int j = 1; j < list.size(); ++j) {
			nextVal = MathUtil.o2d(list.get(j));
			if (preVal == null) {
				preVal = nextVal;
			}else {
				if (nextVal != null) {
					if (preVal > nextVal) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean isDesc(List list) {
		Double preVal = MathUtil.o2d(list.get(0));
		Double nextVal = null;
		for (int j = 1; j < list.size(); ++j) {
			nextVal = MathUtil.o2d(list.get(j));
			if (preVal == null) {
				preVal = nextVal;
			}else {
				if (nextVal != null) {
					if (preVal < nextVal) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public String wave(List list) {
		if (isAsc(list)) {
			return "增长";
		}else if(isDesc(list)) {
			return "降低";
		}else {
			return "波动";
		}
	}

	public String zzl(Object s1, Object s2) {
		Double d1 = MathUtil.o2d(s1);
		Double d2 = MathUtil.o2d(s2);
		if (null == d1 || 
			null == d2 || 
			MathUtil.isNegative(d1) ||
			MathUtil.isNegative(d2) ||
			MathUtil.isZero(d1) ||
			MathUtil.isZero(d2)) {
			return "--";
		} else {
			BigDecimal b = new BigDecimal((d1 / d2 - 1) * 100);
			String sRet = b.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString();
			return ratioText(sRet + "%");
		}
	}
	
	
	public String wcl(String sub, String base) {
		Double dSub = MathUtil.o2d(sub);
		Double dBase = MathUtil.o2d(base);
		Double ret = MathUtil.division(dSub, dBase);
		if (ret != null && ret >= 0) {
			BigDecimal b = new BigDecimal(ret * 100);
			String sRet = b.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString();
			return sRet + "%";
		}
		return "--";
	}
	
	public List<String> lwcl(List<String> l1, List<String> l2) {
		List<String> ret = new ArrayList<String>();
		for (int i = 0; i < l1.size(); ++i) {
			ret.add(wcl(l1.get(i), l2.get(i)));
		}
		return ret;
	}
	
	public String plus(String s1, String s2) {
		Double d1 = MathUtil.o2d(s1);
		Double d2 = MathUtil.o2d(s2);
		Double dr = MathUtil.sum(d1, 2d);
		if (dr != null) {
			BigDecimal b = new BigDecimal(dr);
			String sRet = b.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString();
			return sRet;
		}
		return "--";
	}
	
	public List<String> lplus(List<String> l1, List<String> l2) {
		List<String> ret = new ArrayList<String>();
		for (int i = 0; i < l1.size(); ++i) {
			ret.add(plus(l1.get(i), l2.get(i)));
		}
		return ret;
	}
	
	
	public List<String> lplus3(List<String> l1, List<String> l2, List<String> l3) {
		return lplus(lplus(l1, l2), l3);
	}
	
	public List<String> lplus4(List<String> l1, List<String> l2, List<String> l3, List<String> l4) {
		return lplus(lplus3(l1, l2, l3), l4);
	}
	
	public String sum(List<String> l) {
		Double dRet = null;
		for (int i = 0; i < l.size(); ++i) {
			dRet = MathUtil.sum(MathUtil.o2d(l.get(i)), dRet);
		}
		if (dRet != null) {
			BigDecimal b = new BigDecimal(dRet);
			String sRet = b.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString();
			return sRet;
		}
		return "--";
	}
	
	public String cmp(String s1, String s2) {
		Double d1 = MathUtil.o2d(s1);
		Double d2 = MathUtil.o2d(s2);
		if (d1 != null && d2 != null) {
			if (MathUtil.isZero(d1 - d2)) {
				return "持平";
			}
			if (d1 > d2) {
				return "降低";
			} 
			if (d1 < d2) {	
				return "增长";
			}
		}
		return "";
	}
}
