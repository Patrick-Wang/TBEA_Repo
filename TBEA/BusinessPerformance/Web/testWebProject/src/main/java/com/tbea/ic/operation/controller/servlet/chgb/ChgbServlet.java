package com.tbea.ic.operation.controller.servlet.chgb;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.service.chgb.ChgbServiceImpl;
import com.tbea.ic.operation.service.chgb.ChgbService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "chgb")
public class ChgbServlet {
	@Resource(name=ChgbServiceImpl.NAME)
	ChgbService chgbService;

	CompanyManager companyManager;
	List<Company> chgbComps = new ArrayList<Company>();
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager companyManager){
		this.companyManager = companyManager;
		chgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.SBGS));
		chgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.HBGS));
		chgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XBC));
		chgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.TBGS));
		chgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.LLGS));
		chgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XLC));
		chgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS));
	}
	
	@RequestMapping(value = "chzmb/update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getChgbzmb_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = chgbService.getChzmb(d, companyManager.getBMDBOrganization().getCompany(comp));
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "chjykcb/update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getChjykcb_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = chgbService.getChjykcb(d, companyManager.getBMDBOrganization().getCompany(comp));
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "show.do", method = RequestMethod.GET)
	public ModelAndView getChgb(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		CompanySelection compSel = new CompanySelection(true, chgbComps);
		compSel.select(map);
		
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		
		return new ModelAndView("chgb/chgb", map);
	}
	
	
}
