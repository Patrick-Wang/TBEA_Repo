package com.tbea.ic.operation.controller.servlet.cwyjsf;

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
import com.tbea.ic.operation.common.Url;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.service.cwyjsf.CwyjsfService;
import com.tbea.ic.operation.service.cwyjsf.CwyjsfServiceImpl;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.util.tools.DateUtil;

@Controller
@RequestMapping(value = "cwyjsf")
public class CwyjsfServlet {
	@Resource(name=CwyjsfServiceImpl.NAME)
	CwyjsfService cwyjsfService;


	CompanyManager companyManager;
	List<Company> COMPS = new ArrayList<Company>();
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager companyManager){
		this.companyManager = companyManager;
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.SBGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.HBGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.XBC));
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.TBGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.LLGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.DLGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.XLC));
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.XNYGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.XTNYGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.TCNY));
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.NDGS));
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.JCKGS_JYDW));
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.GJGCGS_GFGS));
	}
	
	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@RequestMapping(value = {"show.do", "v2/show.do"})
	public ModelAndView getShow(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.FinanceLookup);
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "cwyjsf/cwyjsf", map);
	}
	
	//每月3到五号零点触发
	@Scheduled(cron="0 0 0 3-5 * ?")
	public void scheduleImport(){
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime().toLocaleString() + "CwcpdlmlServlet import data from NC");
		cal.add(Calendar.MONTH, -1);
		Date d = DateUtil.toDate(cal);
		cwyjsfService.importFromNC(d, COMPS);
	}
	
	@RequestMapping(value = "nctest.do")
	public @ResponseBody byte[] nctest(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date d = DateUtil.toDate(cal);
		if (!(request.getParameter("date") == null)){
			d = Date.valueOf(request.getParameter("date"));
		}
		cwyjsfService.importFromNC(d, COMPS);
		return "OK".getBytes("utf-8");
	}
}