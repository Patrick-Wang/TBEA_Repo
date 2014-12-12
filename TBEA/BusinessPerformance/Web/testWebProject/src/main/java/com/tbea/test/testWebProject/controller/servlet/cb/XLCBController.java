package com.tbea.test.testWebProject.controller.servlet.cb;

import java.sql.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.tbea.test.testWebProject.common.CompanyManager;
import com.tbea.test.testWebProject.common.Organization;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.common.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.service.cb.XLCBService;


@Controller
@RequestMapping(value = "xlcb")
public class XLCBController {
	@Autowired
	private XLCBService service;
	
	@RequestMapping(value = "tb.do", method = RequestMethod.GET)
	public ModelAndView getZbcb(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar date = Calendar.getInstance();  
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		List<String[][]> tbs = service.getTbmx(Date.valueOf(year + "-" + month + "-1"));
		String[][] aTbmx = tbs.get(0);
		String[][] aJttb = tbs.get(1);
		String[][] aGstb = tbs.get(2);
		Map<String, Object> map = new HashMap<String, Object>();
		String tbmx = JSONArray.fromObject(aTbmx).toString().replace("null", "0.00");
		String jttb = JSONArray.fromObject(aJttb).toString().replace("null", "0.00");
		String gstb = JSONArray.fromObject(aGstb).toString().replace("null", "0.00");
		map.put("tbmx", tbmx);
		map.put("jttb", jttb);
		map.put("gstb", gstb);
		map.put("month", month);
		return new ModelAndView("cb_xl", map);
	}
	
	@RequestMapping(value = "zx.do", method = RequestMethod.GET)
	public ModelAndView getByqzxcb(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar preMonth = Calendar.getInstance();  
		preMonth.add(Calendar.MONTH, -1);
		int month = preMonth.get(Calendar.MONTH) + 1;
		int year = preMonth.get(Calendar.YEAR);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		Organization org = CompanyManager.getOperationOrganization();
		String[][] name_ids = Util.getCompanyNameAndIds(org.getCompany(CompanyType.SBDCY).getSubCompanys());
		map.put("names", name_ids[0]);
		map.put("ids", name_ids[1]);
		map.put("company_size", name_ids[0].length);
		return new ModelAndView("cb_zx_xl", map);
	}
	
	@RequestMapping(value = "wg.do", method = RequestMethod.GET)
	public ModelAndView getByqwgcb(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar preMonth = Calendar.getInstance();  
		preMonth.add(Calendar.MONTH, -1);
		int month = preMonth.get(Calendar.MONTH) + 1;
		int year = preMonth.get(Calendar.YEAR);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		Organization org = CompanyManager.getOperationOrganization();
		String[][] name_ids = Util.getCompanyNameAndIds(org.getCompany(CompanyType.SBDCY).getSubCompanys());
		map.put("names", name_ids[0]);
		map.put("ids", name_ids[1]);
		map.put("company_size", name_ids[0].length);
		return new ModelAndView("cb_wg_xl", map);
	}
}
