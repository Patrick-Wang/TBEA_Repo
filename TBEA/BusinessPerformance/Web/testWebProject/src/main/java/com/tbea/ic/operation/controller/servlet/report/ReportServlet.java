package com.tbea.ic.operation.controller.servlet.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.reportframe.component.Component;
import com.tbea.ic.operation.reportframe.component.ComponentManager;
import com.tbea.ic.operation.reportframe.component.controller.ControllerRequest;
import com.tbea.ic.operation.reportframe.component.controller.ControllerSession;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.reportframe.component.service.Service;
import com.tbea.ic.operation.reportframe.component.service.Transaction;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

@Controller
@RequestMapping(value = "report")
public class ReportServlet {

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	ComponentManager compMgr = new ComponentManager();

	@Autowired
	Transaction trProxy;
	
	@Autowired
	ExtendAuthorityService extendAuthService;
	
	public static interface AuthManager{
		Map<String, Object> getAuthedCompanies(int authType);
	}
	
	public static interface CompanyTypeIdMapper{
		int getId(int type);
		int getType(int id);
	}
	
	@RequestMapping(value = "{controllor}.do")
	public ModelAndView ssoLogin(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("controllor") String controllor) {
		com.tbea.ic.operation.reportframe.component.controller.Controller controller = compMgr.getController(controllor);
		if (null != controller){
			Context context = new Context();
			context.put(Component.REQUEST, new ControllerRequest(request));
			context.put(Component.RESPONSE, response);
			context.put(Component.CALENDAR, new EasyCalendar());
			context.put(Component.SESSION, new ControllerSession(request.getSession()));
			context.put("transactionManager", trProxy);
			context.put("authManager", new AuthManager(){

				@Override
				public Map<String, Object> getAuthedCompanies(int authType) {
					List<Company> comps = extendAuthService.getAuthedCompanies(SessionManager.getAccount(request.getSession()), authType);
					CompanySelection compSel = new CompanySelection(true, comps);
					Map<String, Object> map = new HashMap<String, Object>();
					compSel.select(map);
					return map;
				}
				
			});
			
			context.put("compTypeIdMapper", new CompanyTypeIdMapper(){

				@Override
				public int getId(int type) {
					return companyManager.getBMDBOrganization().getCompany(CompanyType.valueOf(type)).getId();
				}

				@Override
				public int getType(int id) {
					return companyManager.getBMDBOrganization().getCompany(id).getType().ordinal();
				}
				
			});
			
			controller.run(context);
			return (ModelAndView) context.get(com.tbea.ic.operation.reportframe.component.controller.Controller.MODEL_AND_VIEW);
		}
		return null;
	}
}
