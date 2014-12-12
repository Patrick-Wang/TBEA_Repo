package com.tbea.test.testWebProject.controller.servlet.yszkjgqk;

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

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.common.companys.CompanyManager;
import com.tbea.test.testWebProject.common.companys.Organization;
import com.tbea.test.testWebProject.common.companys.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.service.cqk.CQKService;
import com.tbea.test.testWebProject.service.yqkbhqs.YQKBHQSService;
import com.tbea.test.testWebProject.service.yszkjgqk.YSZKJGQKService;

@Controller
@RequestMapping(value = "yszkjgqk")
public class YSZKJGQKController {
	@Autowired
	private YSZKJGQKService service;
	private String view = "yszkjgqkb";
	
	
	@RequestMapping(value = "yszkjgqk_update.do", method = RequestMethod.GET)
	public @ResponseBody String getYqkbhqs_update(HttpServletRequest request,
			HttpServletResponse response) {
		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));
		String companyId = request.getParameter("companyId");
		int cid = Integer.parseInt(companyId);
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + 1);
		Organization org = CompanyManager.getOperationOrganization();
		Company comp = org.getCompany(CompanyType.valueOf(cid));
		
		String table = JSONArray.fromObject(service.getYszkjg(d, comp)).toString().replace("null", "0.00");
		String bar = JSONArray.fromObject(service.getWdqtbbh(d, comp)).toString().replace("null", "0.00");
		String line = JSONArray.fromObject(service.getJetbbh(d, comp)).toString().replace("null", "0.00");
		
		return table + "##" + bar + "##" + line;
	}
	
	@RequestMapping(value = "yszkjgqk.do", method = RequestMethod.GET)
	public ModelAndView getYqkbhqs(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + now.get(Calendar.DAY_OF_MONTH));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		Organization org = CompanyManager.getOperationOrganization();
		String[][] name_ids = Util.getCompanyNameAndIds(org.getCompany(CompanyType.SBDCY).getSubCompanys());
		map.put("names", name_ids[0]);
		map.put("ids", name_ids[1]);
		map.put("company_size", name_ids[0].length);
		return new ModelAndView(view, map);
	}

	
}
