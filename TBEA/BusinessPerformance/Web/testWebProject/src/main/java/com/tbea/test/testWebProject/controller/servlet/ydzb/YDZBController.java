package com.tbea.test.testWebProject.controller.servlet.ydzb;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.test.testWebProject.service.blhtdqqkhz.BLHTDQQKHZService;
import com.tbea.test.testWebProject.service.ydzb.Company;
import com.tbea.test.testWebProject.service.ydzb.YDZBService;

@Controller
@RequestMapping(value = "ydzb")
public class YDZBController {

	@Autowired
	private YDZBService service;

	@RequestMapping(value = "hzb_zbhz.do", method = RequestMethod.GET)
	public ModelAndView getHzb_zbhz(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + now.get(Calendar.DAY_OF_MONTH));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		String hzb_zbhz = JSONArray.fromObject(service.getHzb_zbhzData(d)).toString().replace("null", "0.00");
		map.put("hzb_zbhz", hzb_zbhz);
		return new ModelAndView("hzb_zbhz", map);
	}
	
	
//	@RequestMapping(value = "gcy_zbhz/{month}/{year}", method = RequestMethod.GET)
//	public @ResponseBody String getGcy_zbhz(  @PathVariable("month") String m, @PathVariable("year") String y) {
//		int month = Integer.parseInt(m);
//		int year = Integer.parseInt(y);
//		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + "1");
//		String gcy_zbhz = JSONArray.fromObject(service.getGcy_zbhzData(d)).toString().replace("null", "0");
//		return gcy_zbhz;
//	}
	
	@RequestMapping(value = "gcy_zbhz.do", method = RequestMethod.GET)
	public ModelAndView getGcy_zbhz(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + "1");
		String gcy_zbhz = JSONArray.fromObject(service.getGcy_zbhzData(d)).toString().replace("null", "0.00");
		map.put("gcy_zbhz", gcy_zbhz);
		return new ModelAndView("gcy_zbhz", map);
	}
	
	@RequestMapping(value = "gdw_zbhz.do", method = RequestMethod.GET)
	public ModelAndView getGdw_zbhz(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + now.get(Calendar.DAY_OF_MONTH));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		String gdw_zbhz = JSONArray.fromObject(service.getGdw_zbhzData(d)).toString().replace("null", "0.00");
		map.put("gdw_zbhz", gdw_zbhz);
		return new ModelAndView("gdw_zbhz", map);
	}

	
	@RequestMapping(value = "xjlrb.do", method = RequestMethod.GET)
	public ModelAndView getXjlrb(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);
		int day = now.get(Calendar.DAY_OF_MONTH);
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + day);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		map.put("day",  day);
		String xjlrb = JSONArray.fromObject(service.getXjlrbData(d)).toString().replace("null", "0.00");
		map.put("xjlrb", xjlrb);
		return new ModelAndView("xjlrb", map);
	}
	
	
	@RequestMapping(value = "yszkrb_qkb.do", method = RequestMethod.GET)
	public ModelAndView getYszkrb_qkb(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + now.get(Calendar.DAY_OF_MONTH));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		String yszkrb_qkb = JSONArray.fromObject(service.getYszkrb_qkbData(d)).toString().replace("null", "0.00");
		map.put("yszkrb_qkb", yszkrb_qkb);
		return new ModelAndView("yszkrb_qkb", map);
	}
	
	
	private String getZbhz_overviewData(Date d, int companyId, String zbid){
		String zbhz_overview_yd = JSONArray.fromObject(service.getYdZbhz_overviewData(d, Company.get(companyId), zbid)).toString().replace("null", "0.00");
		String zbhz_overview_jd = JSONArray.fromObject(service.getJdZbhz_overviewData(d, Company.get(companyId), zbid)).toString().replace("null", "0.00");
		String zbhz_overview_nd = JSONArray.fromObject(service.getNdZbhz_overviewData(d, Company.get(companyId), zbid)).toString().replace("null", "0.00");
		String zbhz_overview_ydtb = JSONArray.fromObject(service.getYdtbZbhz_overviewData(d, Company.get(companyId), zbid)).toString().replace("null", "0.00");
		String zbhz_overview_jdtb = JSONArray.fromObject(service.getJdtbZbhz_overviewData(d, Company.get(companyId), zbid)).toString().replace("null", "0.00");
		return "{\"yd\":" + zbhz_overview_yd + ", \"jd\" : " + zbhz_overview_jd + ", \"nd\":"+ zbhz_overview_nd +" , \"ydtb\":"+ zbhz_overview_ydtb +", \"jdtb\":" + zbhz_overview_jdtb + "}";
	}
	
	@RequestMapping(value = "zbhz_overview_update.do", method = RequestMethod.GET)
	public @ResponseBody String updateZbhz_overview(HttpServletRequest request,
			HttpServletResponse response) {
		String companyId = request.getParameter("companyId");
		if (companyId == null){
			companyId = Company.Type.JT.ordinal() + "";
		}
		
		int cid = Integer.parseInt(companyId);
		String zb = request.getParameter("zb");
		if (zb == null){
			zb = "5";
		}
		
		Calendar now = Calendar.getInstance();  
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + now.get(Calendar.DAY_OF_MONTH));
		return getZbhz_overviewData(d, cid, zb);
	}
	
	
	
	@RequestMapping(value = "zbhz_overview.do", method = RequestMethod.GET)
	public ModelAndView getZbhz_overview(HttpServletRequest request,
			HttpServletResponse response) {
		String companyId = request.getParameter("companyId");
		if (companyId == null){
			companyId = Company.Type.JT.ordinal() + "";
		}
		
		int cid = Integer.parseInt(companyId);
		String zb = request.getParameter("zb");
		if (zb == null){
			zb = "5";
		}
		
		Calendar now = Calendar.getInstance();  
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + now.get(Calendar.DAY_OF_MONTH));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		//map.put("zbhz_overview", getZbhz_overviewData(d, cid, zb));
		map.put("zbid", zb);
		map.put("zbmc", service.getZbmc(zb));
		Company coms[] = Company.getAll();
		for (int i = 0;  i < coms.length; ++i){
			map.put("id_" + i, i);
			map.put("name_" + i, coms[i].getName());
		}
		return new ModelAndView("zbhz_overview", map);
	}
}
