package com.tbea.ic.operation.controller.servlet.wlydd;

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
@RequestMapping(value = "wlydd")
public class WlyddServlet {
	
	CompanyManager companyManager;
	List<Company> sbdComps = new ArrayList<Company>();
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager companyManager){
		this.companyManager = companyManager;
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.SBGS));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.HBGS));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XBC));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.TBGS));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.LLGS));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.XLC));
		sbdComps.add(companyManager.getBMDBOrganization().getCompany(CompanyType.DLGS));
	}
	
	@RequestMapping(value = "show.do")
	public ModelAndView getSbdddcbjpcqk(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		CompanySelection compSel = new CompanySelection(true, sbdComps);
		compSel.select(map);
		return new ModelAndView("wlyddqk/wlyddqk", map);
	}
	
	@RequestMapping(value = "entry.do")
	public ModelAndView getByqkglyddEntry(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		CompanySelection compSel = new CompanySelection(true, sbdComps);
		compSel.select(map);
		return new ModelAndView("wlyddqk/wlyddqkEntry", map);
	}
	
}
