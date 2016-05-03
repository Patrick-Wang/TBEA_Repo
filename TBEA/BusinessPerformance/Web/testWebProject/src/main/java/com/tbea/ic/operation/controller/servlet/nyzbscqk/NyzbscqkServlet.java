package com.tbea.ic.operation.controller.servlet.nyzbscqk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;

@Controller
@RequestMapping(value = "nyzbscqk")
public class NyzbscqkServlet {

	CompanyManager companyManager;
	List<Company> COMPS = new ArrayList<Company>();
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager companyManager){
		this.companyManager = companyManager;
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.NLTK));
		COMPS.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XJNY));
	}
	
	@RequestMapping(value = "show.do")
	public ModelAndView getShow(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		CompanySelection compSel = new CompanySelection(true, COMPS);
		compSel.select(map);
		return new ModelAndView("nyzbscqk/nyzbscqk", map);
	}
	
	@RequestMapping(value = "entry.do")
	public ModelAndView getEntry(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		CompanySelection compSel = new CompanySelection(true, COMPS);
		compSel.select(map);
		return new ModelAndView("nyzbscqk/nyzbscqkEntry", map);
	}
}