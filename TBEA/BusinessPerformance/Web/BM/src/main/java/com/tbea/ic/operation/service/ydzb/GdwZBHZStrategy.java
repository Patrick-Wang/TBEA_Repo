package com.tbea.ic.operation.service.ydzb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.entity.YDZBBean;

public class GdwZBHZStrategy extends ZbfdwhzZBHZStrategy {
	
	private static Map<Integer, Integer> qybh_gdwMap = new HashMap<Integer, Integer>();
	
	public GdwZBHZStrategy(CompanyManager companyManager){
		super(companyManager);
		if (qybh_gdwMap.isEmpty()){
			Company cy;
			List<Company> cys;
			Organization org = companyManager.getOperationOrganization();
			for (int i = 0, j = 0; i < CY_TYPES.length; ++i){
				if (CY_TYPES[i] != null){
					cy = org.getCompany(CY_TYPES[i]);				
					if (!cy.getSubCompanies().isEmpty() && cy.getType() != CompanyType.JT){
						cys = cy.getSubCompanies();
						for (int k = 0; k < cys.size(); ++k){
							qybh_gdwMap.put(cys.get(k).getId(), j++);
						}
					}
					qybh_gdwMap.put(cy.getId(), j++);
				}
				else{
					qybh_gdwMap.put(GFHJ, j++);
				}
			}
		}
	}

	
	
	@Override
	public String[][] getZBHZData(List<YDZBBean> ydzbs) {
		String[][] result = new String[21 * zbbh_gcygdwMap.size()][13];
		if (ydzbs != null){
			fillData(result, ydzbs, 21, qybh_gdwMap);
		}
		return result;
	}
	
}
