package com.tbea.ic.operation.controller.servlet.cwgbjyxxjl;

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
import com.tbea.ic.operation.service.cwgbjyxxjl.CwgbjyxxjlService;

@Controller
@RequestMapping(value = "cwgbjyxxjl")
public class CwgbjyxxjlServlet {

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
	CwgbjyxxjlService cwgbjyxxjlService;
	
	@RequestMapping(value = "show.do")
	public ModelAndView getShow(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		CompanySelection compSel = new CompanySelection(true, COMPS);
		compSel.select(map);
		return new ModelAndView("cwgbjyxxjl/cwgbjyxxjl", map);
	}
	
	@RequestMapping(value = "entry.do")
	public ModelAndView getEntry(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		CompanySelection compSel = new CompanySelection(true, COMPS);
		compSel.select(map);
		return new ModelAndView("cwgbjyxxjl/cwgbjyxxjlEntry", map);
	}
	
	//每月3到五号零点触发
	@Scheduled(cron="0 0 0 3-5 * ?")
	public void scheduleImport(){
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime().toLocaleString() + "yszkgb import data from NC");
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);

		cwgbjyxxjlService.importFromNC(d, COMPS);
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
		cwgbjyxxjlService.importFromNC(d, COMPS);
		return "OK".getBytes("utf-8");
	}
}