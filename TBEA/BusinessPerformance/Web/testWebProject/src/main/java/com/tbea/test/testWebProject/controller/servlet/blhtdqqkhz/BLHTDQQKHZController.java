package com.tbea.test.testWebProject.controller.servlet.blhtdqqkhz;

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
import com.tbea.test.testWebProject.service.blhtdqqkhz.BLHTDQQKHZService;

@Controller
@RequestMapping(value = "blhtdqqkhz")
public class BLHTDQQKHZController {

	@Autowired
	private BLHTDQQKHZService service;

	private String view = "blhtdqqkhzb";

	@RequestMapping(value = "blhtdqqkhz_update.do", method = RequestMethod.GET)
	public @ResponseBody String getBlhtdqqkhzbById_update(HttpServletRequest request,
			HttpServletResponse response) {
		Date d = DateSelection.getDate(request);
		Organization org = CompanyManager.getOperationOrganization();
		Company comp = org.getCompany(CompanySelection.getCompany(request));
		List<String[][]> result = new ArrayList<String[][]>();
		result.add(service.getBlyeqs(d, comp));
		result.add(service.getBlhtdqqk(d, comp));
		String jsonRet = JSONArray.fromObject(result).toString().replace("null", "0.00");
		return jsonRet;
	}
	
	@RequestMapping(value = "blhtdqqkhz.do", method = RequestMethod.GET)
	public ModelAndView getBlhtdqqkhzbById(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();

		DateSelection dateSel = new DateSelection(service.getLatestDate(), true, false);
		dateSel.select(map);
		
		Organization org = CompanyManager.getOperationOrganization();
		CompanySelection compSelection = new CompanySelection(true,
				org.getCompany(CompanyType.SBDCY).getSubCompanys());
		compSelection.setFirstCompany(CompanyType.XL);
		compSelection.select(map);
		
		return new ModelAndView(view, map);
	}

}
