package com.tbea.ic.operation.service.ydzb;

import com.tbea.ic.operation.common.companys.CompanyManager;

public class ZBHZStrategyFactory {
	public static ZBHZStrategy createGcyZBHZStrategy(CompanyManager companyManager){
		return new GcyZBHZStrategy(companyManager);
	} 
	
	public static ZBHZStrategy createGdwZBHZStrategy(CompanyManager companyManager){
		return new GdwZBHZStrategy(companyManager);
	} 
}
