package com.tbea.ic.operation.controller.servlet.ztyszkfx;

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

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.service.cqk.CQKService;
import com.tbea.ic.operation.service.hkjhjg.HKJHJGService;
import com.tbea.ic.operation.service.syhkjhzxqk.SYHKJHZXQKService;
import com.tbea.ic.operation.service.tbbzjqk.TBBZJQKService;
import com.tbea.ic.operation.service.ztyszkfx.ZTYSZKFXService;

@Controller
@RequestMapping(value = "ztyszkfx")
public class ZTYSZKFXController {

	@Autowired
	private ZTYSZKFXService service;
	
	
	@RequestMapping(value = "ztyszkfx_update.do", method = RequestMethod.GET)
	public @ResponseBody String getZtyszkfx_update(HttpServletRequest request,
			HttpServletResponse response) {
//		int year = Integer.parseInt(request.getParameter("year"));
//		int month = Integer.parseInt(request.getParameter("month"));
//		Date d = java.sql.Date.valueOf(year + "-" +  month + "-" + 1);
		Date d = DateSelection.getDate(request);
		
		String syhkjhzxqk = JSONArray.fromObject(service.getZtyszkfxData(d)).toString().replace("null", "0.00");
		return syhkjhzxqk;
	}
	

	
	@RequestMapping(value = "ztyszkfx.do", method = RequestMethod.GET)
	public ModelAndView getZtyszkfx(HttpServletRequest request,
			HttpServletResponse response) {	
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(service.getLatestDate(), true, false);
		dateSel.select(map);
		return new ModelAndView("ztyszkfx", map);
	}
}
