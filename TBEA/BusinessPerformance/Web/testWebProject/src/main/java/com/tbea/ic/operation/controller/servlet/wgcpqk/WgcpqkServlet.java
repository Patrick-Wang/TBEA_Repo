package com.tbea.ic.operation.controller.servlet.wgcpqk;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
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
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.tbea.ic.operation.service.wgcpqk.WgcpqkService;

@Controller
@RequestMapping(value = "wgcpqk")
public class WgcpqkServlet {
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	WgcpqkService wgcpqkService;
	
	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@RequestMapping(value = {"show.do", "v2/show.do"})
	public ModelAndView getWgcpqk(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		List<Company> comps = extendAuthService.getAuthedCompaniesForSbd(
				SessionManager.getAccount(request.getSession()),
				AuthType.SbdgbLookup);
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "wgcpqk/wgcpqk", map);
	}
	
	@RequestMapping(value = {"entry.do", "v2/entry.do"})
	public ModelAndView getWgcpqkEntry(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.SbdgbEntry);
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "wgcpqk/wgcpqkEntry", map);
	}
	
	//每月3到五号零点触发
	@Scheduled(cron="0 0 0 3-5 * ?")
	public void scheduleImport(){
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime().toLocaleString() + "yszkgb import data from NC");
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);

		wgcpqkService.importByqFromNC(d, companyManager.getBMDBOrganization().getCompanyByType(CompanyType.SBGS));
		wgcpqkService.importByqFromNC(d, companyManager.getBMDBOrganization().getCompanyByType(CompanyType.HBGS));
		wgcpqkService.importByqFromNC(d, companyManager.getBMDBOrganization().getCompanyByType(CompanyType.XBC));
		wgcpqkService.importByqFromNC(d, companyManager.getBMDBOrganization().getCompanyByType(CompanyType.TBGS));

		
		wgcpqkService.importXlFromNC(d, companyManager.getBMDBOrganization().getCompanyByType(CompanyType.LLGS));
		wgcpqkService.importXlFromNC(d, companyManager.getBMDBOrganization().getCompanyByType(CompanyType.XLC));
		wgcpqkService.importXlFromNC(d, companyManager.getBMDBOrganization().getCompanyByType(CompanyType.DLGS));
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

		
		wgcpqkService.importByqFromNC(d, companyManager.getBMDBOrganization().getCompanyByType(CompanyType.SBGS));
		wgcpqkService.importByqFromNC(d, companyManager.getBMDBOrganization().getCompanyByType(CompanyType.HBGS));
		wgcpqkService.importByqFromNC(d, companyManager.getBMDBOrganization().getCompanyByType(CompanyType.XBC));
		wgcpqkService.importByqFromNC(d, companyManager.getBMDBOrganization().getCompanyByType(CompanyType.TBGS));

		
		wgcpqkService.importXlFromNC(d, companyManager.getBMDBOrganization().getCompanyByType(CompanyType.LLGS));
		wgcpqkService.importXlFromNC(d, companyManager.getBMDBOrganization().getCompanyByType(CompanyType.XLC));
		wgcpqkService.importXlFromNC(d, companyManager.getBMDBOrganization().getCompanyByType(CompanyType.DLGS));
		return "OK".getBytes("utf-8");
	}
}
