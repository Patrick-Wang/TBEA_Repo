package com.tbea.ic.operation.service.report;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public class CompanyHelper {
	public List<Integer> getIds(List<Company> cts){
		List<Integer> ids = new ArrayList<Integer>();
		for (Company ct : cts){
			ids.add(ct.getId());
		}
		return ids;
	}
	
	public List<String> getNames(List<Company> cts){
		List<String> vals = new ArrayList<String>();
		for (Company ct : cts){
			vals.add(ct.getName());
		}
		return vals;
	}
	
}
