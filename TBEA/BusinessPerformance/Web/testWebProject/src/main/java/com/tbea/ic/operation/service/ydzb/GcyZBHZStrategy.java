package com.tbea.ic.operation.service.ydzb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.entity.YDZBBean;

public class GcyZBHZStrategy extends ZbfdwhzZBHZStrategy {
	
	private static Map<Integer, Integer> qybh_gcyMap = new HashMap<Integer, Integer>();
	
	public GcyZBHZStrategy(CompanyManager companyManager){
		super(companyManager);
		if (qybh_gcyMap.isEmpty()){
			Organization org = companyManager.getOperationOrganization();
			for (int i = 0, j = 0; i < CY_TYPES.length; ++i){
				if (CY_TYPES[i] != null){
					qybh_gcyMap.put(org.getCompany(CY_TYPES[i]).getId(), i);
				}
				else{
					qybh_gcyMap.put(GFHJ, i);
				}
			}
		}
	}

	@Override
	public String[][] getZBHZData(List<YDZBBean> ydzbs) {
		String[][] result = new String[7 * zbbh_gcygdwMap.size()][13];
		if (ydzbs != null){
			fillData(result, ydzbs, 7, qybh_gcyMap);
		}
		return result;
	}
}
