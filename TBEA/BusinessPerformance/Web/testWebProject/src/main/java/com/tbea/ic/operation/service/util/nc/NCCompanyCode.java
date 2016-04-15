package com.tbea.ic.operation.service.util.nc;

import java.util.HashMap;
import java.util.Map;

import com.tbea.ic.operation.common.companys.CompanyType;

public class NCCompanyCode {
	final static Map<String, CompanyType> companyMap = new HashMap<String, CompanyType>();
	static {
		companyMap.put("0202AA000000", CompanyType.SBGS);
		companyMap.put("0203AA000000", CompanyType.HBGS);
		companyMap.put("CC02", CompanyType.XBC);
		companyMap.put("0303AA000000", CompanyType.LLGS);
		companyMap.put("0304AA000000", CompanyType.DLGS);
		companyMap.put("CC03", CompanyType.XLC);
		companyMap.put("040203AA0000", CompanyType.XNYGS);
		companyMap.put("040202AA0000", CompanyType.XTNYGS);
		companyMap.put("CC11", CompanyType.TCNY);
		companyMap.put("CC10", CompanyType.NDGS);
		companyMap.put("060100000000", CompanyType.JCKGS_JYDW);
		companyMap.put("CC04", CompanyType.GJGCGS_GFGS);
	}
	public static String getCode(CompanyType type){
		for (String key : companyMap.keySet()){
			if (companyMap.get(key) == type){
				return key;
			}
		}
		return null;
	}
	
	public static CompanyType getType(String code){
		return companyMap.get(code);
	}
}
