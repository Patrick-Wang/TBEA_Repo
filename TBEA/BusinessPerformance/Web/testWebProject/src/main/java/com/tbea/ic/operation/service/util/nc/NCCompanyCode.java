package com.tbea.ic.operation.service.util.nc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;

public class NCCompanyCode {
	final static Map<String, CompanyType> companyMap = new HashMap<String, CompanyType>();
	static {
		companyMap.put("0202AA000000", CompanyType.SBGS);
		companyMap.put("0203AA000000", CompanyType.HBGS);
		companyMap.put("0201AA000000", CompanyType.XBC);
		companyMap.put("0204AA000000", CompanyType.TBGS);
		companyMap.put("0303AA000000", CompanyType.LLGS);
		companyMap.put("0304AA000000", CompanyType.DLGS);
		companyMap.put("030100000000", CompanyType.XLC);
		companyMap.put("040203AA0000", CompanyType.XNYGS);
		companyMap.put("040202AA0000", CompanyType.XTNYGS);
		companyMap.put("0501AA000000", CompanyType.TCNY);
		companyMap.put("070100000000", CompanyType.NDGS);
		companyMap.put("060100000000", CompanyType.JCKGS_JYDW);
		companyMap.put("060400000000", CompanyType.GJGCGS_GFGS);
		companyMap.put("050200000000", CompanyType.XJNY);
		companyMap.put("050101010000 ", CompanyType.NLTK);
	}
	public static String getCode(CompanyType type){
		for (Entry<String, CompanyType> entry : companyMap.entrySet()){
			if (entry.getValue() == type){
				return entry.getKey();
			}
		}
		return null;
	}
	
	public static CompanyType getType(String code){
		return companyMap.get(code);
	}
	
	public static List<String> toCodeList(List<Company> comps){
		List<String> ret = new ArrayList<String>();
		for (Company comp : comps){
			String code = NCCompanyCode.getCode(comp.getType());
			if (null != code){
				ret.add("\'" + code + "\'");
			}
		}
		return ret;
	}
}
