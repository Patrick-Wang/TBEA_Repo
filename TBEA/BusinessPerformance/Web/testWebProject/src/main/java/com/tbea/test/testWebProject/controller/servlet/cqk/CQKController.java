package com.tbea.test.testWebProject.controller.servlet.cqk;

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

import com.tbea.test.testWebProject.common.CompanySelection;
import com.tbea.test.testWebProject.common.DateSelection;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.common.companys.CompanyManager;
import com.tbea.test.testWebProject.common.companys.Organization;
import com.tbea.test.testWebProject.common.companys.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.service.cqk.CQKService;

@Controller
@RequestMapping(value = "CQK")
public class CQKController {

	@Autowired
	private CQKService cqkService;

	private String view = "cqkPage";

	private String commandName = "result";

	@RequestMapping(value = "importCQK.do", method = RequestMethod.GET)
	public ModelAndView importCQK(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = cqkService.importCQK();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}
	
	
	@RequestMapping(value = "cqk_update.do", method = RequestMethod.GET)
	public @ResponseBody String getBlhtdqqkhzbById_update(HttpServletRequest request,
			HttpServletResponse response) {
//		int month = Integer.parseInt(request.getParameter("month"));
//		int year = Integer.parseInt(request.getParameter("year"));
//		String companyId = request.getParameter("companyId");
//		int cid = Integer.parseInt(companyId);
//		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + 1);
//		
		
		Date d = DateSelection.getDate(request);
		Organization org = CompanyManager.getOperationOrganization();
		Company comp = org.getCompany(CompanySelection.getCompany(request));
		
//		Organization org = CompanyManager.getOperationOrganization();
//		Company comp = org.getCompany(CompanyType.valueOf(cid));
		List<String[][]> result = new ArrayList<String[][]>();
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
		
		Organization org = CompanyManager.getOperationOrganization();
		CompanySelection compSelection = new CompanySelection(true,
				org.getCompany(CompanyType.SBDCY).getSubCompanys());
		compSelection.select(map);

		return new ModelAndView("cqk", map);
	}
}
