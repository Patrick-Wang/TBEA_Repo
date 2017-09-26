package com.tbea.ic.operation.controller.servlet.syhkjhzxqk;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.service.cqk.CQKService;
import com.tbea.ic.operation.service.hkjhjg.HKJHJGService;
import com.tbea.ic.operation.service.syhkjhzxqk.SYHKJHZXQKService;

@Controller
@RequestMapping(value = "syhkjhzxqk")
public class SYHKJHZXQKController {

	@Autowired
	private SYHKJHZXQKService service;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@RequestMapping(value = "syhkjhzxqk_update.do", method = RequestMethod.GET)
	public @ResponseBody String getSyhkjhzxqk_update(HttpServletRequest request,
			HttpServletResponse response) {
		Date d = DateSelection.getDate(request);
//		Organization org = companyManager.getOperationOrganization();
//		Company comp = org.getCompany(CompanySelection.getCompany(request));
		
		List<String[][]> hkjhs = new ArrayList<String[][]>();
		
		CompanyType compType = CompanySelection.getCompany(request);
		Company comp = companyManager.getOperationOrganization().getCompanyByType(compType);
		List<String[][]> result = new ArrayList<String[][]>();
		if (null == comp) {
			comp = companyManager.getVirtualYSZKOrganization().getCompanyByType(compType);
			if (null != comp) {
				List<Company> comps = comp.getSubCompanies();
				hkjhs.add(service.getSyhkjhzxqkData(d, comps));
				hkjhs.add(service.getHkjhzxqkXjData(d, comps));
			}
		}
		else {
			hkjhs.add(service.getSyhkjhzxqkData(d, comp));
			hkjhs.add(service.getHkjhzxqkXjData(d, comp));
		}
				
		//List<String[][]> hkjhs = new ArrayList<String[][]>();
//		hkjhs.add(service.getSyhkjhzxqkData(d, comp));
//		hkjhs.add(service.getHkjhzxqkXjData(d, comp));
		String syhkjhzxqk = JSONArray.fromObject(hkjhs).toString().replace("null", "0.00").replace("--", "0.00");

		return syhkjhzxqk;
	}
	

	
	@RequestMapping(value = "syhkjhzxqk.do", method = RequestMethod.GET)
	public ModelAndView getSyhkjhzxqk(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(service.getLatestDate(), true, false);
		dateSel.select(map);

//		Organization org = companyManager.getOperationOrganization();
//		CompanySelection compSel = new CompanySelection(true, org.getCompany(CompanyType.SBDCY).getSubCompanys());
//		compSel.setFirstCompany(CompanyType.HB);
//		compSel.select(map);
		
		List<Company> comps = new ArrayList<Company>();
		comps.addAll(companyManager.getOperationOrganization().getCompanyByType(CompanyType.SBDCY).getSubCompanies());
		comps.addAll(companyManager.getVirtualYSZKOrganization().getTopCompany());
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		
		return new ModelAndView("syhkjhzxqk", map);
	}
}
