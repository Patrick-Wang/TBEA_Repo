package com.tbea.ic.operation.controller.servlet.nwbzlqk;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.EasyList;
import com.tbea.ic.operation.common.CompanySelection.Filter;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

@Controller
@RequestMapping(value = "nwbzlqk")
public class NwbzlqkServlet {

	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@RequestMapping(value = "show.do")
	public ModelAndView getShow(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.QualityLookup);
		Organization org = companyManager.getVirtualCYOrg();
		List<Company> byqs = new EasyList<Company>(org.getCompany(CompanyType.BYQCY).getSubCompanies()).clone();
		List<Company> xls = new EasyList<Company>(org.getCompany(CompanyType.XLCY).getSubCompanies()).clone();
		List<Company> pds = new EasyList<Company>(org.getCompany(CompanyType.PDCY).getSubCompanies()).clone();
		CompanySelection compSel = new CompanySelection(false, org.getTopCompany(), new Filter(){

			@Override
			public boolean keep(Company comp) {
				for (Company cp : comps){
					if (cp.getType() == comp.getType()){
						byqs.remove(comp);
						xls.remove(comp);
						pds.remove(comp);
						return true;
					}
				}
				
				if (comp.getType() == CompanyType.BYQCY){
					if (byqs.isEmpty()){
						return true;
					}
				}
				
				if (comp.getType() == CompanyType.XLCY){
					if (xls.isEmpty()){
						return true;
					}
				}
				if (comp.getType() == CompanyType.PDCY){
					if (pds.isEmpty()){
						return true;
					}
				}
				
				return false;
			}

			@Override
			public boolean keepGroup(Company comp) {
				return true;
			}
		});

		compSel.select(map);
		return new ModelAndView("nwbzlqk/nwbzlqk", map);
	}
	
	
	@RequestMapping(value = "entry.do")
	public ModelAndView getEntry(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.QualityEntry);
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		return new ModelAndView("nwbzlqk/nwbzlqkEntry", map);
	}
}