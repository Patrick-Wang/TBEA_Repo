package com.tbea.ic.operation.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;

public class Company15Code {
	final static Map<String, CompanyType> hrCompMap = new HashMap<String, CompanyType>();
	static {
		hrCompMap.put("10100", CompanyType.SBGS);
		hrCompMap.put("10200", CompanyType.HBGS);
		hrCompMap.put("10300", CompanyType.XBC);
		hrCompMap.put("10500", CompanyType.LLGS);
		hrCompMap.put("10600", CompanyType.XLC);
		hrCompMap.put("10700", CompanyType.DLGS);
		hrCompMap.put("11000", CompanyType.TCNY);
		hrCompMap.put("11200", CompanyType.NDGS);
		hrCompMap.put("10800", CompanyType.XNYGS);
		hrCompMap.put("10900", CompanyType.XTNYGS);
		hrCompMap.put("11100", CompanyType.JCKGS_JYDW);
		hrCompMap.put("11300", CompanyType.GJGCGS_GFGS);
		hrCompMap.put("10400", CompanyType.TBGS);
	}
	public static String getCode(CompanyType type){
		for (Entry<String, CompanyType> entry : hrCompMap.entrySet()){
			if (entry.getValue() == type){
				return entry.getKey();
			}
		}
		return null;
	}
	
	public static CompanyType getType(String code){
		return hrCompMap.get(code);
	}
	
	public static List<String> toCodeList(List<Company> comps){
		List<String> ret = new ArrayList<String>();
		for (Company comp : comps){
			String code = Company15Code.getCode(comp.getType());
			if (null != code){
				ret.add("\'" + code + "\'");
			}
		}
		return ret;
	}
}
