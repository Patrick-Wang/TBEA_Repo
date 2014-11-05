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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.test.testWebProject.service.blhtdqqkhz.BLHTDQQKHZService;
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
		String hzb_zbhz = JSONArray.fromObject(service.getHzb_zbhzData(d)).toString().replace("null", "0");
		map.put("hzb_zbhz", hzb_zbhz);
		return new ModelAndView("hzb_zbhz", map);
	}
	
	@RequestMapping(value = "gcy_zbhz.do", method = RequestMethod.GET)
	public ModelAndView getGcy_zbhz(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + now.get(Calendar.DAY_OF_MONTH));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		String gcy_zbhz = JSONArray.fromObject(service.getGcy_zbhzData(d)).toString().replace("null", "0");
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
		String gdw_zbhz = JSONArray.fromObject(service.getGdw_zbhzData(d)).toString().replace("null", "0");
		map.put("gdw_zbhz", gdw_zbhz);
		return new ModelAndView("gdw_zbhz", map);
	}

	
	@RequestMapping(value = "xjlrb.do", method = RequestMethod.GET)
	public ModelAndView getXjlrb(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + now.get(Calendar.DAY_OF_MONTH));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		String xjlrb = JSONArray.fromObject(service.getXjlrbData(d)).toString().replace("null", "0");
		map.put("xjlrb", xjlrb);
		return new ModelAndView("xjlrb", map);
	}
	
	
	@RequestMapping(value = "yszkrb_qkb.do", method = RequestMethod.GET)
	public ModelAndView getYszkrb_qkb(HttpServletRequest request,
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
		String yszkrb_qkb = JSONArray.fromObject(service.getYszkrb_qkbData(d)).toString().replace("null", "0");
		map.put("yszkrb_qkb", yszkrb_qkb);
		return new ModelAndView("yszkrb_qkb", map);
	}
}
