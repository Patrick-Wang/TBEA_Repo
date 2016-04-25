package com.tbea.ic.operation.controller.servlet.cbfx;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.service.cbfx.dmcbfx.DmcbfxService;
import com.tbea.ic.operation.service.cbfx.dmcbfx.DmcbfxServiceImpl;

@Controller
@RequestMapping(value = "cbfx")
public class CbfxServlet {
	@Resource(name=DmcbfxServiceImpl.NAME)
	DmcbfxService dmcbfxService;
	CompanyManager companyManager;
	List<Company> COMPS = new ArrayList<Company>();
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager companyManager){
		this.companyManager = companyManager;
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.NLTK));
	}
	
	@RequestMapping(value = "show.do")
	public ModelAndView getShow(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		CompanySelection compSel = new CompanySelection(true, COMPS);
		compSel.select(map);
		return new ModelAndView("cbfx/cbfx", map);
	}
	
	@RequestMapping(value = "entry.do")
	public ModelAndView getEntry(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		CompanySelection compSel = new CompanySelection(true, COMPS);
		compSel.select(map);
		return new ModelAndView("cbfx/cbfxEntry", map);
	}
	
	//每月3到五号零点触发
	@Scheduled(cron="0 0 0 3-5 * ?")
	public void scheduleImport(){
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime().toLocaleString() + "yszkgb import data from NC");
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);
		dmcbfxService.importFromNC(d, COMPS);
	}
}