package com.tbea.test.testWebProject.service.ydzb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.CompanyGroup;
import com.tbea.test.testWebProject.model.entity.YDZBBean;

public class GdwZBHZStrategy extends ZbfdwhzZBHZStrategy {
	
	private static Map<String, Integer> qybh_gdwMap = new HashMap<String, Integer>();
	
	public GdwZBHZStrategy(){
		if (qybh_gdwMap.isEmpty()){
			Company cy;
			Company[] cys;
			for (int i = 0, j = 0; i < CY_TYPES.length; ++i){
				if (CY_TYPES[i] != null){
					cy = Company.get(CY_TYPES[i]);				
					if (cy instanceof CompanyGroup && cy.getId() != Company.get(Company.Type.JT).getId()){
						cys = ((CompanyGroup)cy).getCompanys();
						for (int k = 0; k < cys.length; ++k){
							qybh_gdwMap.put(cys[k].getId(), j++);
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
