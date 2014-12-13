package com.tbea.test.testWebProject.controller.servlet.rhkqk;

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

import com.tbea.test.testWebProject.service.rhkqk.RHKQKService;

@Controller
@RequestMapping(value = "rhkqk")
public class RHKQKController {

	
	@Autowired
	private RHKQKService service;

	
	@RequestMapping(value = "rhkqk_update.do", method = RequestMethod.GET)
	public @ResponseBody String getXjlrb_update(HttpServletRequest request,
			HttpServletResponse response) {
		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));
		int day = Integer.parseInt(request.getParameter("day"));
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + day);
		String xjlrb = JSONArray.fromObject(service.getRhkqkData(d)).toString().replace("null", "0.00");
		return xjlrb;
	}
	
	@RequestMapping(value = "rhkqk.do", method = RequestMethod.GET)
	public ModelAndView getXjlrb(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int month = 11;//now.get(Calendar.MONTH) + 1;
		int year = 2013;//now.get(Calendar.YEAR);
		int day = 30;//now.get(Calendar.DAY_OF_MONTH);
		int dayCount = now.getActualMaximum(Calendar.DAY_OF_MONTH);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		map.put("day",  day);
		map.put("dayCount",  dayCount);
		return new ModelAndView("rhkqk", map);
	}

}
