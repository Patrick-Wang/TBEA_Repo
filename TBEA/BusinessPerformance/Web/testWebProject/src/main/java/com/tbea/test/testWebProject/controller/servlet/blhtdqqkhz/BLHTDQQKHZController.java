package com.tbea.test.testWebProject.controller.servlet.blhtdqqkhz;

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

@Controller
@RequestMapping(value = "blhtdqqkhz")
public class BLHTDQQKHZController {

	@Autowired
	private BLHTDQQKHZService service;

	private String view = "blhtdqqkhzb";

	private String commandName = "bl";

	@RequestMapping(value = "blhtdqqkhz.do", method = RequestMethod.GET)
	public ModelAndView getBlhtdqqkhzbById(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + now.get(Calendar.DAY_OF_MONTH));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		String blhtdqqk = JSONArray.fromObject(service.getBlhtdqqk(d)).toString().replace("null", "0.00");
		String blyeqs = JSONArray.fromObject(service.getBlyeqs(d)).toString().replace("null", "0.00");
		map.put("blhtdqqk", blhtdqqk);
		map.put("blyeqs", blyeqs);
		return new ModelAndView(view, map);
	}

}
