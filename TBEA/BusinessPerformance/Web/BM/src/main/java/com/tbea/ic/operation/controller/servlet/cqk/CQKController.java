package com.tbea.ic.operation.controller.servlet.cqk;

import java.sql.Date;
import java.util.ArrayList;
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
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.service.cqk.CQKService;

@Controller
@RequestMapping(value = "CQK")
public class CQKController {

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	private CQKService cqkService;

	private String view = "cqkPage";

	private String commandName = "result";


	
	@RequestMapping(value = "cqk_update.do", method = RequestMethod.GET)
	public @ResponseBody String getBlhtdqqkhzbById_update(HttpServletRequest request,
			HttpServletResponse response) {

		Date d = DateSelection.getDate(request);
		CompanyType compType = CompanySelection.getCompany(request);
		List<String[][]> result = new ArrayList<String[][]>();
		Company comp = companyManager.getOperationOrganization().getCompany(compType);
		if (null == comp) {
			comp = companyManager.getVirtualYSZKOrganization().getCompany(compType);
			if (null != comp) {
				result.add(cqkService.getCqkData(d, comp.getSubCompanies()));
				result.add(cqkService.getCompareData(d, comp.getSubCompanies()));
			}
		}
		else {
			result.add(cqkService.getCqkData(d, comp));
			result.add(cqkService.getCompareData(d, comp));
		}
		
		
		result.add(cqkService.getCqkData(d, comp));
		result.add(cqkService.getCompareData(d, comp));
	
		String cqkResult = JSONArray.fromObject(result).toString().replace("null", "0.00");

		return cqkResult;
	}
	

	
	@RequestMapping(value = "cqk.do", method = RequestMethod.GET)
	public ModelAndView getBlhtdqqkhzbById(HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();

		DateSelection dateSel = new DateSelection(cqkService.getLatestDate(), true, false);
		dateSel.select(map);
		
		List<Company> comps = new ArrayList<Company>();
		comps.addAll(companyManager.getOperationOrganization().getCompany(CompanyType.SBDCY).getSubCompanies());
		comps.addAll(companyManager.getVirtualYSZKOrganization().getTopCompany());
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);


		return new ModelAndView("cqk", map);
	}
}
