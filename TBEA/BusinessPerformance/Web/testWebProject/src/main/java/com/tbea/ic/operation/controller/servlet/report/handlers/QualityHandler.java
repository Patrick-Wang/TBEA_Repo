package com.tbea.ic.operation.controller.servlet.report.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.common.ClosureMap;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.querier.QuerierFactory;
import com.tbea.ic.operation.common.querier.ZBStatusQuerier;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.controller.servlet.report.ContextHandler;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.reportframe.component.controller.ControllerRequest;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.service.cpzlqk.CpzlqkService;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

@Component
public class QualityHandler implements ContextHandler {

	@Autowired
	CpzlqkService cpzlqkService;

	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Override
	public void onHandle(Context context) {
		
		context.put("zlzts", new ClosureMap(){

			@Override
			protected boolean validate(List<Object> args) throws Exception {
				return args.size() == 1;
			}

			@Override
			protected Object onGetProp(List<Object> args) throws Exception {
				CompanyType comp = CompanyType.valueOf((Integer) args.get(0));
				Company company = companyManager.getVirtualCYOrg().getCompany(comp);
				HttpServletRequest request = (
						(ControllerRequest) context.get(
								com.tbea.ic.operation.reportframe.component.Component.REQUEST))
							.getRequest();
				List<Integer> zts = new ArrayList<Integer>();
				List<Integer> auths = extendAuthService.getAuths(SessionManager.getAccount(request.getSession()), company);
				ZBStatusQuerier querier = QuerierFactory.createZlApproveQuerier();
				zts.addAll(querier.queryStatus(auths));
				return zts;
			}
		});
	}

}
