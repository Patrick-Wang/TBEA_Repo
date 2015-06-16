package com.tbea.ic.operation.controller.servlet.nczb;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.controller.servlet.ydzb.CompanyTypeFilter;
import com.tbea.ic.operation.service.ydzb.YDZBService;
import com.tbea.ic.operation.service.ydzb.gszb.GszbService;


@Controller
@RequestMapping(value = "NCzb")
public class NCZBController {
	
	@Autowired
	private YDZBService service;
	
	@Autowired
	private GszbService gszbService;
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@RequestMapping(value = "AllCompanysNC_overview.do", method = RequestMethod.GET)
	public ModelAndView getAllCompanysNC_overview(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, false);
		dateSel.select(map);
		return new ModelAndView("hzbNC_zbhz", map);
	}
	
	@RequestMapping(value = "CompanysNC.do", method = RequestMethod.GET)
	public ModelAndView getCompanysNC(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, false);
		dateSel.select(map);
		Organization org = companyManager.getVirtualJYZBOrganization();
		CompanySelection compSel = new CompanySelection(
				false,
				org.getCompany(CompanyType.GFGS).getSubCompanies(), 
				new CompanyTypeFilter(
						gszbService.getCompanies(SessionManager.getAccount(request.getSession(false))), 
						org));

		compSel.select(map, 3);
		return new ModelAndView("hzb_companysNC", map);
	}
	
	
	@RequestMapping(value = "AllCompanysNC_overview_update.do", method = RequestMethod.GET)
	public @ResponseBody String getAllCompanysNC_overview_update(HttpServletRequest request,
			HttpServletResponse response) {

		Date d = DateSelection.getDate(request);
		String ranking_val = "[]";
		
		return ranking_val;
	}
	
	@RequestMapping(value = "CompanysNC_update.do", method = RequestMethod.GET)
	public @ResponseBody String getCompanysNC_update(HttpServletRequest request,
			HttpServletResponse response) {

		Date d = DateSelection.getDate(request);
		String ranking_val = null;
		
		return ranking_val;
	}

}
