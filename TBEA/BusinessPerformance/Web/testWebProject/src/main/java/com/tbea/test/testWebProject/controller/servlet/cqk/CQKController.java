package com.tbea.test.testWebProject.controller.servlet.cqk;

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

@Controller
@RequestMapping(value = "CQK")
public class CQKController {

	@Autowired
	private CQKService cqkService;

	private String view = "cqkPage";

	private String commandName = "result";

	@RequestMapping(value = "importCQK.do", method = RequestMethod.GET)
	public ModelAndView importCQK(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = cqkService.importCQK();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}
	@RequestMapping(value = "cqk.do", method = RequestMethod.GET)
	public ModelAndView getBlhtdqqkhzbById(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + now.get(Calendar.DAY_OF_MONTH));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		String cqk = JSONArray.fromObject(cqkService.getCqkData(d)).toString().replace("null", "0.00");
		String cqkCompare = JSONArray.fromObject(cqkService.getCompareData(d)).toString().replace("null", "0.00");
		map.put("cqk", cqk);
		map.put("cqkCompare", cqkCompare);
		return new ModelAndView("cqk", map);
	}
}
