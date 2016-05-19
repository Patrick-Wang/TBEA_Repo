package com.tbea.ic.operation.service.util.pipe.acc.composite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.companys.Company;

public class CompositeAccDataSource {
	Map<Integer, Map<String, Double[]>> data = new HashMap<Integer, Map<String, Double[]>>();
	
	public CompositeAccDataSource add(Company comp, List<Integer> zbs, List<Double[]> vals){
		for (int i = 0, len = zbs.size(); i < len; ++i){
			add(comp, zbs.get(i), vals.get(i));
		}
		return this;
	}
	
	public CompositeAccDataSource add(Company comp, Integer zb, Double[] vals){
		if (!data.containsKey(zb)){
			data.put(zb, new HashMap<String, Double[]>());
		}
		data.get(zb).put(comp.getUniqueId(), vals);
		return this;
	}
		
	public Double[] getRow(Company comp, Integer zb){
		if (data.containsKey(zb)){
			Map<String, Double[]> compData = data.get(zb);
			if (compData.containsKey(comp.getUniqueId())){
				return compData.get(comp.getUniqueId());
			}
		}
		return null;
	}
	
	public void clear(){
		data.clear();
	}
}
