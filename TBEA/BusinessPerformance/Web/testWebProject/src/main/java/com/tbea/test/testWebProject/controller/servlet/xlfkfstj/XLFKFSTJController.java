package com.tbea.test.testWebProject.controller.servlet.xlfkfstj;

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

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.service.byqfkfstj.BYQFKFSTJService;
import com.tbea.test.testWebProject.service.cqk.CQKService;
import com.tbea.test.testWebProject.service.hkjhjg.HKJHJGService;
import com.tbea.test.testWebProject.service.syhkjhzxqk.SYHKJHZXQKService;
import com.tbea.test.testWebProject.service.tbbzjqk.TBBZJQKService;
import com.tbea.test.testWebProject.service.xlfkfstj.XLFKFSTJService;
import com.tbea.test.testWebProject.service.ztyszkfx.ZTYSZKFXService;

@Controller
@RequestMapping(value = "xlfkfstj")
public class XLFKFSTJController {

	@Autowired
	private XLFKFSTJService service;
	
	
	@RequestMapping(value = "xlfkfstj.do", method = RequestMethod.GET)
	public ModelAndView getZtyszkfx(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		Date d = java.sql.Date.valueOf(now.get(Calendar.YEAR) + "-" +  (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH));
		Map<String, Object> map = new HashMap<String, Object>();
	
		String gw = JSONArray.fromObject(service.getGwData(d)).toString().replace("null", "0.00");
		map.put("gw", gw);
		String nw = JSONArray.fromObject(service.getNwData(d)).toString().replace("null", "0.00");
		map.put("nw", nw);
		String fdw = JSONArray.fromObject(service.getFdwData(d)).toString().replace("null", "0.00");
		map.put("fdw", fdw);
		return new ModelAndView("xl_fkfstj", map);
	}
}
