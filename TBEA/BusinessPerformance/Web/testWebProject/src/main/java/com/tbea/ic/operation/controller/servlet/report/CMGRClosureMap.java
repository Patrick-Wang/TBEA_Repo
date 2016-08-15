package com.tbea.ic.operation.controller.servlet.report;

import java.lang.reflect.Method;
import java.util.List;

import com.tbea.ic.operation.common.ClosureMap;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;

public abstract class CMGRClosureMap extends ClosureMap{


	CompanyManager mgr;
	Organization org;
	public CMGRClosureMap(CompanyManager mgr) {
		super();
		this.mgr = mgr;
	}

	Organization getOrg(String orgName)throws Exception{
		if (org == null){
			String mdName = "get" + orgName.substring(0, 1).toUpperCase() + orgName.substring(1);
			Method md = mgr.getClass().getMethod(mdName);
			org = (Organization) md.invoke(mgr);
		}
		return org;
	}
	
	@Override
	protected Object onGetProp(List<Object> args) throws Exception{
		Object obj = onGetProp(org, args);
		org = null;
		return obj;
	}

	@Override
	protected boolean validate(List<Object> args) throws Exception{
		if (org == null){
			getOrg((String) args.remove(0));
		}
		return onValidating(args);
	}
	
	
	protected abstract boolean onValidating(List<Object> args);

	protected abstract Object onGetProp(Organization org, List<Object> args) throws Exception;

}
