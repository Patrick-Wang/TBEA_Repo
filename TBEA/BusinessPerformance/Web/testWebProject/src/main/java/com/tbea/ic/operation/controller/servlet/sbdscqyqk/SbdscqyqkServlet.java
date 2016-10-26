package com.tbea.ic.operation.controller.servlet.sbdscqyqk;

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
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.tbea.ic.operation.service.sbdscqyqk.xfcpqy.XfcpqyService;
import com.tbea.ic.operation.service.sbdscqyqk.xfcpqy.XfcpqyServiceImpl;
import com.tbea.ic.operation.service.sbdscqyqk.xfscqy.XfscqyService;
import com.tbea.ic.operation.service.sbdscqyqk.xfscqy.XfscqyServiceImpl;

@Controller
@RequestMapping(value = "sbdscqyqk")
public class SbdscqyqkServlet {

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	
	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@Resource(name=XfcpqyServiceImpl.NAME)
	XfcpqyService xfcpqyService;
	
	@Resource(name=XfscqyServiceImpl.NAME)
	XfscqyService xfscqyService;
	
	@RequestMapping(value = "show.do")
	public ModelAndView getShow(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		List<Company> comps = extendAuthService.getAuthedCompaniesForSbd(
				SessionManager.getAccount(request.getSession()),
				AuthType.SbdgbLookup);

		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		return new ModelAndView("sbdscqyqk/sbdscqyqk", map);
	}
	
	@RequestMapping(value = "entry.do")
	public ModelAndView getEntry(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.SbdgbEntry);

		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		return new ModelAndView("sbdscqyqk/sbdscqyqkEntry", map);
	}
	
	@RequestMapping(value = "schedule.do")
	public @ResponseBody byte[] schedule(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);
		if (request.getParameter("date") != null){
			d = Date.valueOf(request.getParameter("date"));
		}
		
		xfcpqyService.importCpqy(d);
		xfscqyService.importScqy(d);
		
		String result = "{\"result\":\"OK\"}";
		return result.getBytes("utf-8");
	}
	
	//每月3到五号零点触发
	@Scheduled(cron="0 0 0 4-5 * ?")
	public void scheduleImport(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);
		xfcpqyService.importCpqy(d);
		xfscqyService.importScqy(d);
	}
	
}