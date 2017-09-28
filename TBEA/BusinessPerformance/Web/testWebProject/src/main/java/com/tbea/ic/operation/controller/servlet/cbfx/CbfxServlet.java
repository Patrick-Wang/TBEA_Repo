package com.tbea.ic.operation.controller.servlet.cbfx;

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
import com.tbea.ic.operation.service.cbfx.dmcbfx.DmcbfxService;
import com.tbea.ic.operation.service.cbfx.dmcbfx.DmcbfxServiceImpl;
import com.tbea.ic.operation.service.cbfx.nymyywmlfx.NymyywmlfxService;
import com.tbea.ic.operation.service.cbfx.nymyywmlfx.NymyywmlfxServiceImpl;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.util.tools.DateUtil;

@Controller
@RequestMapping(value = "cbfx")
public class CbfxServlet {
	@Resource(name=DmcbfxServiceImpl.NAME)
	DmcbfxService dmcbfxService;
	@Resource(name=NymyywmlfxServiceImpl.NAME)
	NymyywmlfxService nymyywmlfxService;
	
	CompanyManager companyManager;
	List<Company> COMPS = new ArrayList<Company>();
	
	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager companyManager){
		this.companyManager = companyManager;
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.NLTK));
		COMPS.add(companyManager.getBMDBOrganization().getCompanyByType(CompanyType.XJNY));
	}
	
	@RequestMapping(value = {"show.do", "v2/show.do"})
	public ModelAndView getShow(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.NygbLookup);
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") +"cbfx/cbfx", map);
	}
	
	@RequestMapping(value = {"entry.do", "v2/entry.do"})
	public ModelAndView getEntry(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.NygbEntry);
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") +"cbfx/cbfxEntry", map);
	}
	
	//每月3到五号零点触发
	@Scheduled(cron="0 0 0 3-5 * ?")
	public void scheduleImport(){
		Calendar cal = Calendar.getInstance();
		//System.out.println(cal.getTime().toLocaleString() + "yszkgb import data from NC");
		cal.add(Calendar.MONTH, -1);
		Date d = DateUtil.toDate(cal);
		dmcbfxService.importFromNC(d, COMPS);
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
		dmcbfxService.importFromNC(d, COMPS);
		return "OK".getBytes("utf-8");
	}
}