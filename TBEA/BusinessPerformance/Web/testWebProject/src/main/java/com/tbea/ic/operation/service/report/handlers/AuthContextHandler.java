package com.tbea.ic.operation.service.report.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frame.script.util.ClosureMap;
import com.frame.script.util.PropMap;
import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.CompanySelection.Filter;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.tbea.ic.operation.service.report.SystemAuthority;
import com.xml.frame.report.component.entity.Context;
import com.xml.frame.report.util.EasyList;

@Component
public class AuthContextHandler extends ControllerContextHandler {

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@Autowired
	ExtendAuthorityService extendAuthService;

	public void onHandle(Context context, HttpServletRequest request, HttpServletResponse resp) {
		
		context.put("sysAuth", new SystemAuthority(companyManager, SessionManager.getAccount(request.getSession()), extendAuthService));
		
		context.put("authCompanies", new ClosureMap(){

			@Override
			public Object onGetProp(List<Object> args) throws Exception {
				Integer authType = (Integer)args.get(0);
				List<Company> comps = extendAuthService.getAuthedCompanies(
						SessionManager.getAccount(request.getSession()), 
						authType);
				List<Integer> compIds = new ArrayList<Integer>();
				for (Company comp : comps){
					compIds.add(comp.getId());
				}
				return compIds;
			}
			@Override
			protected boolean validate(List<Object> args) throws Exception {
				return args.size() == 1;
			}
		});

		
		context.put("authTypes", new ClosureMap(){

			@Override
			public Object onGetProp(List<Object> args) throws Exception {
				Integer authType = (Integer)args.get(0);
				List<Company> comps = extendAuthService.getAuthedCompanies(
						SessionManager.getAccount(request.getSession()), 
						authType);
				List<CompanyType> compIds = new ArrayList<CompanyType>();
				for (Company comp : comps){
					compIds.add(comp.getType());
				}
				return compIds;
			}
			@Override
			protected boolean validate(List<Object> args) throws Exception {
				return args.size() == 1;
			}
		});
		
		context.put("authIds", new ClosureMap(){

			@Override
			public Object onGetProp(List<Object> args) throws Exception {
				Integer authType = (Integer)args.get(0);
				List<Company> comps = extendAuthService.getAuthedCompanies(
						SessionManager.getAccount(request.getSession()), 
						authType);
				List<Integer> compIds = new ArrayList<Integer>();
				for (Company comp : comps){
					compIds.add(comp.getId());
				}
				return compIds;
			}
			@Override
			protected boolean validate(List<Object> args) throws Exception {
				return args.size() == 1;
			}
		});
		
		context.put("auth", new PropMap(){

			@Override
			public Object getProperty(Object key) throws Exception {
				return extendAuthService.hasAuthority(
						SessionManager.getAccount(request.getSession()), 
						(Integer)key);
			}
		});

		context.put("authMgr", new PropMap(){

			@Override
			public Object getProperty(Object key) throws Exception {
				
				if (key instanceof Integer){
					Integer authType = (Integer)key;
					List<Company> comps = extendAuthService.getAuthedCompanies(
							SessionManager.getAccount(request.getSession()), authType);
					if (authType == 43){
						Organization org = companyManager.getVirtualCYOrg();
						List<Company> byqs = new EasyList<Company>(org.getCompany(CompanyType.BYQCY).getSubCompanies()).clone();
						List<Company> xls = new EasyList<Company>(org.getCompany(CompanyType.XLCY).getSubCompanies()).clone();
						CompanySelection compSel = new CompanySelection(false, org.getTopCompany(), new Filter(){

							@Override
							public boolean keep(Company comp) {
								for (Company cp : comps){
									if (cp.getType() == comp.getType()){
										byqs.remove(comp);
										xls.remove(comp);
										return true;
									}
								}
								
								if (comp.getType() == CompanyType.BYQCY){
									if (byqs.isEmpty()){
										return true;
									}
								}
								
								if (comp.getType() == CompanyType.XLCY){
									if (xls.isEmpty()){
										return true;
									}
								}
								
								return false;
							}

							@Override
							public boolean keepGroup(Company comp) {
								return true;
							}
						});
						Map<String, Object> map = new HashMap<String, Object>();
						compSel.select(map);
						return map; 
					}else{
						CompanySelection compSel = new CompanySelection(true, comps);
						Map<String, Object> map = new HashMap<String, Object>();
						compSel.select(map);
						return map;
					}
					
				}else{
					List<Company> comps = new ArrayList<Company>();
					Set<Company> compSet = new HashSet<Company>();
					List<Integer> auths = (List<Integer>) key;
					for (Integer auth : auths){
						compSet.addAll(extendAuthService.getAuthedCompanies(
								SessionManager.getAccount(request.getSession()), auth));
					}
					for (Company comp : compSet){
						comps.add(comp);
					}
					CompanySelection compSel = new CompanySelection(true, comps);
					Map<String, Object> map = new HashMap<String, Object>();
					compSel.select(map);
					return map;
				}
			}
		});
	}

}
