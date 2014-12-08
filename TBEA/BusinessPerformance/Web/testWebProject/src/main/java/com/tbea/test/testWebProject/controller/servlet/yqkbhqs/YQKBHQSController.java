package com.tbea.test.testWebProject.controller.servlet.yqkbhqs;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
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

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.CompanyManager;
import com.tbea.test.testWebProject.common.Organization;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.common.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.service.yqkbhqs.YQKBHQSService;

@Controller
@RequestMapping(value = "yqkbhqs")
public class YQKBHQSController {
	@Autowired
	private YQKBHQSService service;
	private String view = "yqkqsbh";
	
	@RequestMapping(value = "yqkbhqs_update.do", method = RequestMethod.GET)
	public @ResponseBody String getYqkbhqs_update(HttpServletRequest request,
			HttpServletResponse response) {
		int year = Integer.parseInt(request.getParameter("year"));
		String companyId = request.getParameter("companyId");
		int cid = Integer.parseInt(companyId);
		Date d = java.sql.Date.valueOf(year + "-" + 1 + "-" + 1);

		Organization org = CompanyManager.getOperationOrganization();
		Company comp = org.getCompany(CompanyType.valueOf(cid));

		String yqkbhqs = JSONArray.fromObject(service.getYqkbhqsData(d, comp)).toString().replace("null", "0.00");
		return yqkbhqs;
	}

	
	@RequestMapping(value = "yqkbhqs.do", method = RequestMethod.GET)
	public ModelAndView getYqkbhqs(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int year = now.get(Calendar.YEAR);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		Organization org = CompanyManager.getOperationOrganization();
		String[][] name_ids = Util.getCompanyNameAndIds(org.getCompany(CompanyType.SBDCY).getSubCompanys());
		map.put("names", name_ids[0]);
		map.put("ids", name_ids[1]);
		map.put("company_size", name_ids[0].length);
		return new ModelAndView(view, map);
	}

	
}
