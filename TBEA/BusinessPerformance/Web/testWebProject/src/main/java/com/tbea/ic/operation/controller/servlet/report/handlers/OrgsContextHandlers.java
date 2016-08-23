package com.tbea.ic.operation.controller.servlet.report.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.controller.servlet.report.CMGRClosureMap;
import com.tbea.ic.operation.controller.servlet.report.ContextHandler;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

@Component
public class OrgsContextHandlers implements ContextHandler {


	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@Autowired
	ExtendAuthorityService extendAuthService;

	public void onHandle(Context context) {
		context.put("orgs", new CMGRClosureMap(companyManager) {

			@Override
			protected Object onGetProp(Organization org, List<Object> args)
					throws Exception {
				if ("ids".equals(args.get(0))) {
					return org.getCompany(
							CompanyType.valueOf((Integer) args.get(1))).getId();
				} else if ("types".equals(args.get(0))) {
					return org.getCompany((Integer) args.get(1)).getType()
							.ordinal();
				} else if ("companiesByIds".equals(args.get(0))) {
					List<Integer> ids = (List) args.get(1);
					List<Company> comps = new ArrayList<Company>();
					for (Integer id : ids) {
						comps.add(org.getCompany(id));
					}
					return comps;
				} else {
					return null;
				}
			}

			@Override
			protected boolean onValidating(List<Object> args) {
				return args.size() == 2;
			}
		});

	}
}
