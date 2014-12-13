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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.common.companys.CompanyManager;
import com.tbea.test.testWebProject.common.companys.Organization;
import com.tbea.test.testWebProject.common.companys.CompanyManager.CompanyType;
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
	
	@RequestMapping(value = "wg_update.do", method = RequestMethod.GET)
	public  @ResponseBody String  getxlwgcb_update(HttpServletRequest request,
			HttpServletResponse response) {
		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));
		Date d = java.sql.Date.valueOf(year + "-" + month + "-1");
		List<String[][]> wgs = service.getWgmx(Date.valueOf(year + "-" + month + "-1"));
		String[][] aJtwg = wgs.get(1);
		//String[][] aGswg = wgs.get(2);
		String jtwg = JSONArray.fromObject(aJtwg).toString().replace("null", "0.00");
		//String gswg = JSONArray.fromObject(aGswg).toString().replace("null", "0.00");
		return "[" + jtwg  + "]";

	}
	
	@RequestMapping(value = "wg.do", method = RequestMethod.GET)
	public ModelAndView getxlwgcb(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar date = Calendar.getInstance();  
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		List<String[][]> wgs = service.getWgmx(Date.valueOf(year + "-" + month + "-1"));
		String[][] aWgmx = wgs.get(0);
		String[][] aJtwg = wgs.get(1);
		String[][] aGswg = wgs.get(2);
		String[][] aBtdywg = wgs.get(3);
		Map<String, Object> map = new HashMap<String, Object>();
		String wgmx = JSONArray.fromObject(aWgmx).toString().replace("null", "0.00");
		String jtwg = JSONArray.fromObject(aJtwg).toString().replace("null", "0.00");
		String gswg = JSONArray.fromObject(aGswg).toString().replace("null", "0.00");
		String btdywg = JSONArray.fromObject(aBtdywg).toString().replace("null", "0.00");
		map.put("wgmx", wgmx);
		map.put("jtwg", jtwg);
		map.put("gswg", gswg);
		map.put("btdywg", btdywg);
		map.put("month", 8);
		map.put("year", year);
		return new ModelAndView("cb_wg_xl", map);
	}
}
