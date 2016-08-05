package com.tbea.ic.operation.controller.servlet.report;

import java.lang.reflect.Method;
import java.util.List;

import com.tbea.ic.operation.common.ClosureMap;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;

public abstract class CMGRClosureMap extends ClosureMap{


	@Override
	protected boolean validate(List<Object> args) {
		return args.size() == 2;
	}
	
	Organization getOrg(CompanyManager mgr, String orgName)throws Exception{
		String mdName = "get" + orgName.substring(0, 1).toUpperCase() + orgName.substring(1);
		Method md = mgr.getClass().getMethod(mdName);
		return (Organization) md.invoke(mgr);
	}

}
