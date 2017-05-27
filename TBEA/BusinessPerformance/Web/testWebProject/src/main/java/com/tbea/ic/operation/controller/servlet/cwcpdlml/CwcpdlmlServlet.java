package com.tbea.ic.operation.controller.servlet.cwcpdlml;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.service.cwcpdlml.CwcpdlmlService;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

@Controller
@RequestMapping(value = "cwcpdlml")
public class CwcpdlmlServlet {

	CompanyManager companyManager;
	List<Company> COMPS = new ArrayList<Company>();
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager companyManager){
		this.companyManager = companyManager;	
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.SBGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.HBGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XBC));
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.TBGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.LLGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XLC));
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XNYGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XTNYGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.TCNY));
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.NDGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.JCKGS_JYDW));
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.GJGCGS_GFGS));
	}

	@Autowired
	CwcpdlmlService cwcpdlmlService;
	
	@Autowired
	ExtendAuthorityService extAuthServ;
	
	@RequestMapping(value = "show.do")
	public ModelAndView getShow(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		List<Company> comps = new ArrayList<Company>();
		comps.addAll(extAuthServ.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.FinanceLookup));
		
		if (comps.size() > 1){
			comps.add(0, companyManager.getBMDBOrganization().getCompany(CompanyType.GFGS));
		}
		
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		return new ModelAndView("cwcpdlml/cwcpdlml", map);
	}
	
	//每月3到五号零点触发
	@Scheduled(cron="0 0 0 4-5 * ?")
	public void scheduleImport(){
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime().toLocaleString() + "yszkgb import data from NC");
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);
		
		for (Company comp : COMPS){
			cwcpdlmlService.importFromNC(d, comp);
		}
	}
	
	@RequestMapping(value = "nctest.do")
	public @ResponseBody byte[] nctest(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);
		if (!(request.getParameter("date") == null)){
			d = Date.valueOf(request.getParameter("date"));
		}
		for (Company comp : COMPS){
			cwcpdlmlService.importFromNC(d, comp);
		}
		return "OK".getBytes("utf-8");
	}
	
}