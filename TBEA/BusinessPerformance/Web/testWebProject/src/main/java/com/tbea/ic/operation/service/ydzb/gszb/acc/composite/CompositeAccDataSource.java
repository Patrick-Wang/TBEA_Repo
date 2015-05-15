package com.tbea.ic.operation.service.ydzb.gszb.acc.composite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;

public class CompositeAccDataSource {
	Map<Integer, Map<CompanyType, Double[]>> data = new HashMap<Integer, Map<CompanyType, Double[]>>();
	
	public CompositeAccDataSource add(Company comp, List<Integer> zbs, List<Double[]> vals){
		for (int i = 0, len = zbs.size(); i < len; ++i){
			add(comp, zbs.get(i), vals.get(i));
		}
		return this;
	}
	
	public CompositeAccDataSource add(Company comp, Integer zb, Double[] vals){
		if (!data.containsKey(zb)){
			data.put(zb, new HashMap<CompanyType, Double[]>());
		}
		data.get(zb).put(comp.getType(), vals);
		return this;
	}
		
	public Double[] getRow(Company comp, Integer zb){
		if (data.containsKey(zb)){
			Map<CompanyType, Double[]> compData = data.get(zb);
			if (compData.containsKey(comp.getType())){
				return compData.get(comp.getType());
			}
		}
		return null;
	}
	
	public void clear(){
		data.clear();
	}
}
