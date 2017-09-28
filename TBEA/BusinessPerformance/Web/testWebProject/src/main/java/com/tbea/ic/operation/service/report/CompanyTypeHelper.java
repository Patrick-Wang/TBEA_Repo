package com.tbea.ic.operation.service.report;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.CompanyType;

public class CompanyTypeHelper {
	public List<Integer> getIds(List<CompanyType> cts){
		List<Integer> ids = new ArrayList<Integer>();
		for (CompanyType ct : cts){
			ids.add(ct.ordinal());
		}
		return ids;
	}
	
	public List<String> getValues(List<CompanyType> cts){
		List<String> vals = new ArrayList<String>();
		for (CompanyType ct : cts){
			vals.add(ct.getValue());
		}
		return vals;
	}
	
	public CompanyType valueOf(Integer ct){
		return CompanyType.valueOf(ct);
	}
}
