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
import org.springframework.web.servlet.ModelAndView;

import com.tbea.test.testWebProject.service.cqk.CQKService;
import com.tbea.test.testWebProject.service.yqkbhqs.YQKBHQSService;
import com.tbea.test.testWebProject.service.yszkjgqk.YSZKJGQKService;

@Controller
@RequestMapping(value = "yszkjgqk")
public class YSZKJGQKController {
	@Autowired
	private YSZKJGQKService service;
	private String view = "yszkjgqkb";
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
		String table = JSONArray.fromObject(service.getYszkjg(d)).toString().replace("null", "0.00");
		String bar = JSONArray.fromObject(service.getWdqtbbh(d)).toString().replace("null", "0.00");
		String line = JSONArray.fromObject(service.getJetbbh(d)).toString().replace("null", "0.00");

		map.put("table", table);
		map.put("bar", bar);
		map.put("line", line);
		return new ModelAndView(view, map);
	}

	
}
