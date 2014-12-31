package com.tbea.test.testWebProject.controller.servlet.byqfkfstj;

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

import com.tbea.test.testWebProject.service.byqfkfstj.BYQFKFSTJService;

@Controller
@RequestMapping(value = "byqfkfstj")
public class BYQFKFSTJController {

	@Autowired
	private BYQFKFSTJService service;
	
	@RequestMapping(value = "byqfkfstj_update.do", method = RequestMethod.GET)
	public @ResponseBody String getZtyszkfx_update(HttpServletRequest request,
			HttpServletResponse response) {
		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));
		Date d = java.sql.Date.valueOf(year + "-" +  month + "-1");
		List<String[][]> fkfs = new ArrayList<String[][]>();
		fkfs.add(service.getFdwData(d));
		fkfs.add(service.getGwData(d));
		fkfs.add(service.getNwData(d));
		String fkf = JSONArray.fromObject(fkfs).toString().replace("null", "0.00");
		return fkf;
	}
	

	@RequestMapping(value = "byqfkfstj.do", method = RequestMethod.GET)
	public ModelAndView getZtyszkfx(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		Date d = java.sql.Date.valueOf(now.get(Calendar.YEAR) + "-" +  (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH));
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar date = Calendar.getInstance();  
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		map.put("month", month);
		map.put("year", year);
		return new ModelAndView("byq_fkfstj", map);
	}
}
