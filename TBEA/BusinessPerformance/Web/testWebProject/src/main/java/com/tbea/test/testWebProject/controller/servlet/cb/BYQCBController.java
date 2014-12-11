package com.tbea.test.testWebProject.controller.servlet.cb;

import java.sql.Date;
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

import com.tbea.test.testWebProject.common.CompanyManager;
import com.tbea.test.testWebProject.common.Organization;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.common.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.service.cb.BYQCBService;


@Controller
@RequestMapping(value = "byqcb")
public class BYQCBController {
	@Autowired
	private BYQCBService service;
	
	@RequestMapping(value = "tb.do", method = RequestMethod.GET)
	public ModelAndView getByqzbcb(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar date = Calendar.getInstance();  
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		List<String[][]> tbs = service.getTbmx(Date.valueOf(year + "-" + month + "-1"));
		String[][] aTbmx = tbs.get(0);
		String[][] aJttb = tbs.get(1);
		String[][] aGstb = tbs.get(2);
		Map<String, Object> map = new HashMap<String, Object>();
		String tbmx = JSONArray.fromObject(aTbmx).toString().replace("null", "0.00");
		String jttb = JSONArray.fromObject(aJttb).toString().replace("null", "0.00");
		String gstb = JSONArray.fromObject(aGstb).toString().replace("null", "0.00");
		map.put("tbmx", tbmx);
		map.put("jttb", jttb);
		map.put("gstb", gstb);
		map.put("month", month);
		return new ModelAndView("cb_byq", map);
	}
	
	@RequestMapping(value = "zx.do", method = RequestMethod.GET)
	public ModelAndView getByqzxcb(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar date = Calendar.getInstance();  
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		List<String[][]> zxs = service.getZxmx(Date.valueOf(year + "-" + month + "-1"));
		String[][] aZxmx = zxs.get(0);
		String[][] aJtzx = zxs.get(1);
		String[][] aGszx = zxs.get(2);
		Map<String, Object> map = new HashMap<String, Object>();
		String zxmx = JSONArray.fromObject(aZxmx).toString().replace("null", "0.00");
		String jtzx = JSONArray.fromObject(aJtzx).toString().replace("null", "0.00");
		String gszx = JSONArray.fromObject(aGszx).toString().replace("null", "0.00");
		map.put("zxmx", zxmx);
		map.put("jtzx", jtzx);
		map.put("gszx", gszx);
		map.put("month", month);
		return new ModelAndView("cb_zx_byq", map);
	}
	
	@RequestMapping(value = "wg_update.do", method = RequestMethod.GET)
	public @ResponseBody String  getByqwgcb_update(HttpServletRequest request,
			HttpServletResponse response) {
		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));
		Date d = java.sql.Date.valueOf(year + "-" + month + "-1");
		List<String[][]> wgs = service.getWgmx(Date.valueOf(year + "-" + month + "-1"));
		String[][] aJtwg = wgs.get(1);
		String[][] aBydywg = wgs.get(3);
		String jtwg = JSONArray.fromObject(aJtwg).toString().replace("null", "0.00");
		String byqwg = JSONArray.fromObject(aBydywg).toString().replace("null", "0.00");
		return "[" + jtwg  + ","+ byqwg + "]";
	
	}
	
	
	@RequestMapping(value = "wg.do", method = RequestMethod.GET)
	public ModelAndView getByqwgcb(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar date = Calendar.getInstance();  
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		List<String[][]> wgs = service.getWgmx(Date.valueOf(year + "-" + month + "-1"));
		String[][] aWgmx = wgs.get(0);
		String[][] aJtwg = wgs.get(1);
		String[][] aGswg = wgs.get(2);
		String[][] aBydywg = wgs.get(3);
		Map<String, Object> map = new HashMap<String, Object>();
		String wgmx = JSONArray.fromObject(aWgmx).toString().replace("null", "0.00");
		String jtwg = JSONArray.fromObject(aJtwg).toString().replace("null", "0.00");
		String gswg = JSONArray.fromObject(aGswg).toString().replace("null", "0.00");
		String bydywg = JSONArray.fromObject(aBydywg).toString().replace("null", "0.00");
		map.put("wgmx", wgmx);
		map.put("jtwg", jtwg);
		map.put("gswg", gswg);
		map.put("bydywg", bydywg);
		map.put("month", month);
		map.put("year", year);
		return new ModelAndView("cb_wg_byq", map);
	}
}
