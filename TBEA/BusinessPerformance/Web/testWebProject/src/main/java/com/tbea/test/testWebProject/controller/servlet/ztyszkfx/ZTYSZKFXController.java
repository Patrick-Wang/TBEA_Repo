package com.tbea.test.testWebProject.controller.servlet.ztyszkfx;

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
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.service.cqk.CQKService;
import com.tbea.test.testWebProject.service.hkjhjg.HKJHJGService;
import com.tbea.test.testWebProject.service.syhkjhzxqk.SYHKJHZXQKService;
import com.tbea.test.testWebProject.service.tbbzjqk.TBBZJQKService;
import com.tbea.test.testWebProject.service.ztyszkfx.ZTYSZKFXService;

@Controller
@RequestMapping(value = "ztyszkfx")
public class ZTYSZKFXController {

	@Autowired
	private ZTYSZKFXService service;
	
	
	@RequestMapping(value = "ztyszkfx_update.do", method = RequestMethod.GET)
	public @ResponseBody String getZtyszkfx_update(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int year = Integer.parseInt(request.getParameter("year"));
		Date d = java.sql.Date.valueOf(year + "-" +  (now.get(Calendar.MONTH) + 1) + "-" + 1);
		
		String syhkjhzxqk = JSONArray.fromObject(service.getZtyszkfxData(d)).toString().replace("null", "0.00");
		return syhkjhzxqk;
	}
	

	
	@RequestMapping(value = "ztyszkfx.do", method = RequestMethod.GET)
	public ModelAndView getZtyszkfx(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int year = now.get(Calendar.YEAR);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		return new ModelAndView("ztyszkfx", map);
	}
}
