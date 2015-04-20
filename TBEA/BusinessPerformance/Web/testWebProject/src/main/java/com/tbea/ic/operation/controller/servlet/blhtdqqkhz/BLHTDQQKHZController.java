package com.tbea.ic.operation.controller.servlet.blhtdqqkhz;

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
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.blhtdqqkhz.BLHTDQQKHZService;

@Controller
@RequestMapping(value = "blhtdqqkhz")
public class BLHTDQQKHZController {

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	private BLHTDQQKHZService service;

	private String view = "blhtdqqkhzb";

	@RequestMapping(value = "blhtdqqkhz_update.do", method = RequestMethod.GET)
	public @ResponseBody String getBlhtdqqkhzbById_update(HttpServletRequest request,
			HttpServletResponse response) {
		Date d = DateSelection.getDate(request);
//		Organization org = companyManager.getOperationOrganization();
//		Company comp = org.getCompany(CompanySelection.getCompany(request));
		List<String[][]> result = new ArrayList<String[][]>();
//		result.add(service.getBlyeqs(d, comp));
//		result.add(service.getBlhtdqqk(d, comp));
		
		
		CompanyType compType = CompanySelection.getCompany(request);
		Company comp = companyManager.getOperationOrganization().getCompany(compType);
		if (null == comp) {
			comp = companyManager.getVirtualYSZKOrganization().getCompany(compType);
			if (null != comp) {
				result.add(service.getBlyeqs(d, comp.getSubCompanys()));
				result.add(service.getBlhtdqqk(d, comp.getSubCompanys()));
			}
		}
		else {
			result.add(service.getBlyeqs(d, comp));
			result.add(service.getBlhtdqqk(d, comp));
		}
		
		
		String jsonRet = JSONArray.fromObject(result).toString().replace("null", "0.00");
		return jsonRet;
	}
	
	@RequestMapping(value = "blhtdqqkhz.do", method = RequestMethod.GET)
	public ModelAndView getBlhtdqqkhzbById(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();

		DateSelection dateSel = new DateSelection(service.getLatestDate(), true, false);
		dateSel.select(map);
		
		
		List<Company> comps = new ArrayList<Company>();
		comps.addAll(companyManager.getOperationOrganization().getCompany(CompanyType.SBDCY).getSubCompanys());
		comps.addAll(companyManager.getVirtualYSZKOrganization().getTopCompany());
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		
//		Organization org = companyManager.getOperationOrganization();
//		CompanySelection compSelection = new CompanySelection(true,
//				org.getCompany(CompanyType.SBDCY).getSubCompanys());
//		compSelection.setFirstCompany(CompanyType.XL);
//		compSelection.select(map);
		
		return new ModelAndView(view, map);
	}

}
