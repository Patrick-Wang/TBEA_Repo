package com.tbea.ic.operation.controller.servlet.hkjhjg;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.cqk.CQKService;
import com.tbea.ic.operation.service.hkjhjg.HKJHJGService;

@Controller
@RequestMapping(value = "hkjhjg")
public class HKJHJGController {

	@Autowired
	private HKJHJGService service;


	
	@RequestMapping(value = "hkjhjg_update.do", method = RequestMethod.GET)
	public @ResponseBody String getHkjhjg_update(HttpServletRequest request,
			HttpServletResponse response) {
//		int month = Integer.parseInt(request.getParameter("month"));
//		int year = Integer.parseInt(request.getParameter("year"));
//		String companyId = request.getParameter("companyId");
//		int cid = Integer.parseInt(companyId);
//		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + 1);
//		
//		Organization org = CompanyManager.getOperationOrganization();
//		Company comp = org.getCompany(CompanyType.valueOf(cid));
		
		
		Date d = DateSelection.getDate(request);
		Organization org = CompanyManager.getOperationOrganization();
		Company comp = org.getCompany(CompanySelection.getCompany(request));
		List<String[][]> result = new ArrayList<String[][]>();
		result.add(service.getHkjhjgData(d, comp));
		result.add(new String[][]{service.getHkjhztData(d, comp)});
		result.add(new String[][]{service.getHkjhxzData(d, comp)});
		String hkjh = JSONArray.fromObject(result).toString().replace("null", "0.00");		
		return hkjh;
	}
	

	
	@RequestMapping(value = "hkjhjg.do", method = RequestMethod.GET)
	public ModelAndView getHkjhjg(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(service.getLatestDate(), true, false);
		dateSel.select(map);

		Organization org = CompanyManager.getOperationOrganization();
		CompanySelection compSel = new CompanySelection(true, org.getCompany(CompanyType.SBDCY).getSubCompanys());
		compSel.setFirstCompany(CompanyType.HB);
		compSel.select(map);

		return new ModelAndView("hkjhjg", map);
	}
}
