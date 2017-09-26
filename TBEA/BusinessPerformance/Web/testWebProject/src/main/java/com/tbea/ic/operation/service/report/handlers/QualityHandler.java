package com.tbea.ic.operation.service.report.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frame.script.util.ClosureMap;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.querier.QuerierFactory;
import com.tbea.ic.operation.common.querier.ZBStatusQuerier;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.service.cpzlqk.CpzlqkService;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.xml.frame.report.component.entity.Context;

@Component
public class QualityHandler extends ControllerContextHandler {

	@Autowired
	CpzlqkService cpzlqkService;

	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Override
	void onHandle(Context context, HttpServletRequest request, HttpServletResponse resp) {
		context.put("zlzts", new ClosureMap(){

			@Override
			protected boolean validate(List<Object> args) throws Exception {
				return args.size() == 1;
			}

			@Override
			protected Object onGetProp(List<Object> args) throws Exception {
				CompanyType comp = CompanyType.valueOf((Integer) args.get(0));
				Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
				List<Integer> zts = new ArrayList<Integer>();
				List<Integer> auths = null;
				if (company.getType() == CompanyType.BYQCY || company.getType() == CompanyType.XLCY || company.getType() == CompanyType.PDCY) {
					auths = extendAuthService.getAuths(SessionManager.getAccount(request.getSession()));
				}else {
					auths = extendAuthService.getAuths(SessionManager.getAccount(request.getSession()), company);
				}
				
				ZBStatusQuerier querier = QuerierFactory.createZlApproveQuerier();
				zts.addAll(querier.queryStatus(auths));
				return zts;
			}
		});
	}

}
