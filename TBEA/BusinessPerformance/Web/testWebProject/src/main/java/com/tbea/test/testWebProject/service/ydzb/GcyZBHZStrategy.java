package com.tbea.test.testWebProject.service.ydzb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.entity.YDZBBean;

public class GcyZBHZStrategy extends ZbfdwhzZBHZStrategy {
	
	private static Map<String, Integer> qybh_gcyMap = new HashMap<String, Integer>();
	
	public GcyZBHZStrategy(){
		if (qybh_gcyMap.isEmpty()){
			for (int i = 0, j = 0; i < CY_TYPES.length; ++i){
				if (CY_TYPES[i] != null){
					qybh_gcyMap.put(Company.get(CY_TYPES[i]).getId(), i);
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
