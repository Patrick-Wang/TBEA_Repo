package com.tbea.ic.operation.service.report.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frame.script.util.ClosureMap;
import com.tbea.ic.operation.common.companys.BMDepartmentDB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.controller.servlet.report.CMGRClosureMap;
import com.tbea.ic.operation.controller.servlet.report.ContextHandler;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.xml.frame.report.component.entity.Context;

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
					return org.getCompanyByType(
							CompanyType.valueOf((Integer) args.get(1))).getId();
				} else if ("types".equals(args.get(0))) {
					return org.getCompanyById((Integer) args.get(1)).getType()
							.ordinal();
				} else if ("companiesByIds".equals(args.get(0))) {
					List<Integer> ids = (List) args.get(1);
					List<Company> comps = new ArrayList<Company>();
					for (Integer id : ids) {
						comps.add(org.getCompanyById(id));
					}
					return comps;
				} else if ("namesByIds".equals(args.get(0))) {
					List<Integer> ids = (List) args.get(1);
					List<String> names = new ArrayList<String>();
					for (Integer id : ids) {
						names.add(org.getCompanyById(id).getName());
					}
					return names;
				} else if ("allComps".equals(args.get(0))) {
					return ((BMDepartmentDB)org).getAllComps();
				} else {
					return null;
				}
			}

			@Override
			protected boolean onValidating(List<Object> args) {
				return args.size() == 2;
			}
		});
		
		context.put("BMDBJydw", BMDepartmentDB.getMainlyJydw(companyManager));
		
		context.put("BMDBXmgs", new ClosureMap(){

			@Override
			protected boolean validate(List<Object> args) throws Exception {
				return args.size() == 1;
			}

			@Override
			protected Object onGetProp(List<Object> args) throws Exception {
				List<Company> jydws =  (List) args.get(0);
				List<Company> xmgs = new ArrayList<Company>();
				for (Company jydw : jydws){
					if (!jydw.getSubCompanies().isEmpty()){
						xmgs.addAll(jydw.getSubCompanies());
					}
				}
				return xmgs;
			}
			
		});

	}
}
