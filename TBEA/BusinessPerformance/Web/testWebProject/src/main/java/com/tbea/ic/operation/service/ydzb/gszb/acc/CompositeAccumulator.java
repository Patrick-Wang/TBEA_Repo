package com.tbea.ic.operation.service.ydzb.gszb.acc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;

public class CompositeAccumulator implements IAccumulator {

	private Map<String, Double[]> srcs = new HashMap<String, Double[]>();
	
	public CompositeAccumulator addSrc(Company comp, GSZB zb, Double[] vals){
		return addSrc(comp, zb.ordinal(), vals);
	}
	
	public CompositeAccumulator addSrc(Company comp, List<Integer> zbs, List<Double[]> vals){
		for (int i = 0, len = zbs.size(); i < len; ++i){
			addSrc(comp, zbs.get(i), vals.get(i));
		}
		return this;
	}
	
	public CompositeAccumulator addSrc(Company comp, Integer zb, Double[] vals){
		srcs.put(zb + comp.getType().name(), vals);
		return this;
	}
	
	public CompositeAccumulator addSrc(Company comp, List<Integer> zbs, Double[] vals){
		for (int i = 0, len = zbs.size(); i < len; ++i){
			addSrc(comp, zbs.get(i), vals);
		}
		return this;
	}
	
	@SuppressWarnings("null")
	private Double sum(Integer zb, List<Company> companies, int col){
		Double dRet = null;
		String key;
		Double dTmp;
		for (int i = 0, len = companies.size(); i < len; ++i){
			key = zb + companies.get(i).getType().name();
			if (srcs.containsKey(key)){
				dTmp = srcs.get(key)[col];
				if (null != dTmp){
					dRet = Util.valueOf(dRet) + dTmp;
				}
			}
		}
		return dRet;
	}
	
	@Override
	public List<Double> compute(int col, Date start, Date end, List<Integer> zbs,
			List<Company> companies) {
		List<Double> ret = new ArrayList<Double>();
		for (int i = 0, len = zbs.size(); i < len; ++i){
			ret.add(sum(zbs.get(i), companies, col));
		}
		return ret;
	}

}
