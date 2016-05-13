package com.tbea.ic.operation.controller.servlet.wgcpqk;

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
import com.tbea.ic.operation.service.wgcpqk.WgcpqkService;

@Controller
@RequestMapping(value = "wgcpqk")
public class WgcpqkServlet {
	
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
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XLC));
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS));
	}
	
	@Autowired
	WgcpqkService wgcpqkService;
	
	@RequestMapping(value = "show.do")
	public ModelAndView getWgcpqk(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		CompanySelection compSel = new CompanySelection(true, COMPS);
		compSel.select(map);
		return new ModelAndView("wgcpqk/wgcpqk", map);
	}
	
	@RequestMapping(value = "entry.do")
	public ModelAndView getWgcpqkEntry(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		CompanySelection compSel = new CompanySelection(true, COMPS);
		compSel.select(map);
		return new ModelAndView("wgcpqk/wgcpqkEntry", map);
	}
	
	//每月3到五号零点触发
	@Scheduled(cron="0 0 0 3-5 * ?")
	public void scheduleImport(){
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime().toLocaleString() + "yszkgb import data from NC");
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);

		List<Company> comps = new ArrayList<Company>();
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.SBGS));
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.HBGS));
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XBC));
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.TBGS));
		
		
		wgcpqkService.importByqFromNC(d, comps);
		
		comps.clear();
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.LLGS));
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XLC));
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS));
		
		wgcpqkService.importXlFromNC(d, comps);
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
		List<Company> comps = new ArrayList<Company>();
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.SBGS));
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.HBGS));
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XBC));
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.TBGS));
		
		
		wgcpqkService.importByqFromNC(d, comps);
		
		comps.clear();
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.LLGS));
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XLC));
		comps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS));
		
		wgcpqkService.importXlFromNC(d, comps);
		return "OK".getBytes("utf-8");
	}
}
