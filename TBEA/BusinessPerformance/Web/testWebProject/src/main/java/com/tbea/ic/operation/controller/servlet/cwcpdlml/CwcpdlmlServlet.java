package com.tbea.ic.operation.controller.servlet.cwcpdlml;

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
@RequestMapping(value = "cwcpdlml")
public class CwcpdlmlServlet {

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
	
	@RequestMapping(value = "show.do")
	public ModelAndView getShow(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		CompanySelection compSel = new CompanySelection(true, COMPS);
		compSel.select(map);
		return new ModelAndView("cwcpdlml/cwcpdlml", map);
	}
	
	@RequestMapping(value = "entry.do")
	public ModelAndView getEntry(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		CompanySelection compSel = new CompanySelection(true, COMPS);
		compSel.select(map);
		return new ModelAndView("cwcpdlml/cwcpdlmlEntry", map);
	}
}