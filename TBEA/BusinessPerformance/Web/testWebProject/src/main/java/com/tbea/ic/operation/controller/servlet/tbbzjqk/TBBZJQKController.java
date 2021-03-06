package com.tbea.ic.operation.controller.servlet.tbbzjqk;

import java.sql.Date;
import java.util.HashMap;
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
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.service.tbbzjqk.TBBZJQKService;

@Controller
@RequestMapping(value = "tbbzjqk")
public class TBBZJQKController {

	@Autowired
	private TBBZJQKService service;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@RequestMapping(value = "tbbzjqk_update.do", method = RequestMethod.GET)
	public @ResponseBody String getTbbzjqk_update(HttpServletRequest request,
			HttpServletResponse response) { 
	
		
		Date d = DateSelection.getDate(request);
		Organization org = companyManager.getOperationOrganization();
		Company comp = org.getCompanyByType(CompanySelection.getCompany(request));
		
		String syhkjhzxqk = JSONArray.fromObject(service.getTbbzjqkData(d, comp)).toString().replace("null", "0.00");
		return syhkjhzxqk;
	}
	

	
	@RequestMapping(value = "tbbzjqk.do", method = RequestMethod.GET)
	public ModelAndView getTbbzjqk(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(service.getLatestDate(), true, false);
		dateSel.select(map);
		Organization org = companyManager.getOperationOrganization();
		CompanySelection compSel = new CompanySelection(true, org.getCompanyByType(CompanyType.SBDCY).getSubCompanies());
		compSel.select(map);
		return new ModelAndView("tbbzjqk", map);
	}
}
