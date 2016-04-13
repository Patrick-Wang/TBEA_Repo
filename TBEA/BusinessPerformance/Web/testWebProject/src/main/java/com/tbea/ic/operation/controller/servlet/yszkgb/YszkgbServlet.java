package com.tbea.ic.operation.controller.servlet.yszkgb;

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

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.service.yszkgb.YszkgbService;
import com.tbea.ic.operation.service.yszkgb.YszkgbServiceImpl;

@Controller
@RequestMapping(value = "yszkgb")
public class YszkgbServlet {
	@Resource(name=YszkgbServiceImpl.NAME)
	YszkgbService yszkgbService;

	
	CompanyManager companyManager;
	List<Company> yszkgbComps = new ArrayList<Company>();
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager companyManager){
		this.companyManager = companyManager;
		yszkgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.SBGS));
		yszkgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.HBGS));
		yszkgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XBC));
		yszkgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.TBGS));
		yszkgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.LLGS));
		yszkgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XLC));
		yszkgbComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS));
	}
	
	@RequestMapping(value = "zmb/update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getYqysysfx_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		List<List<String>> result = yszkgbService.getZmb(d, companyManager.getBMDBOrganization().getCompany(comp));
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}

	@RequestMapping(value = "show.do", method = RequestMethod.GET)
	public ModelAndView getYszkgb(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		CompanySelection compSel = new CompanySelection(true, yszkgbComps);
		compSel.select(map);
		
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		
		return new ModelAndView("yszkgb/yszkgb", map);
	}
	
}
