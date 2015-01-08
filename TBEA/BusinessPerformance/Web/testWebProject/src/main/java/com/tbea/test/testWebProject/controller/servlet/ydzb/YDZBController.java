package com.tbea.test.testWebProject.controller.servlet.ydzb;

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

import com.tbea.test.testWebProject.common.CompanySelection;
import com.tbea.test.testWebProject.common.DateSelection;
import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.common.companys.CompanyManager;
import com.tbea.test.testWebProject.common.companys.Organization;
import com.tbea.test.testWebProject.common.companys.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.service.ydzb.YDZBService;

@Controller
@RequestMapping(value = "ydzb")
public class YDZBController {


	@Autowired
	private YDZBService service;

	@RequestMapping(value = "hzb_zbhz_update.do", method = RequestMethod.GET)
	public @ResponseBody String getHzb_zbhz_update(HttpServletRequest request,
			HttpServletResponse response) {
		Date d = DateSelection.getDate(request);
		String hzb_zbhz = JSONArray.fromObject(service.getHzb_zbhzData(d)).toString().replace("null", "0.00");
		return hzb_zbhz;
	}
	
	@RequestMapping(value = "hzb_zbhz.do", method = RequestMethod.GET)
	public ModelAndView getHzb_zbhz(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(service.getLatestHzbDate(), true, false);
		dateSel.select(map);
		return new ModelAndView("hzb_zbhz", map);
	}
	
	@RequestMapping(value = "gcy_zbhz_update.do", method = RequestMethod.GET)
	public @ResponseBody String getGcy_zbhz_update( HttpServletRequest request,
			HttpServletResponse response) {
		Date d = DateSelection.getDate(request);
		String gcy_zbhz = JSONArray.fromObject(service.getGcy_zbhzData(d)).toString().replace("null", "0.00");
		return gcy_zbhz;
	}
	
	@RequestMapping(value = "gcy_zbhz.do", method = RequestMethod.GET)
	public ModelAndView getGcy_zbhz(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(service.getLatestGcyDate(), true, false);
		dateSel.select(map);
		return new ModelAndView("gcy_zbhz", map);
	}
	
	
	@RequestMapping(value = "gdw_zbhz_update.do", method = RequestMethod.GET)
	public  @ResponseBody String getGdw_zbhz_update(HttpServletRequest request,
			HttpServletResponse response) {
		Date d = DateSelection.getDate(request);
		String gdw_zbhz = JSONArray.fromObject(service.getGdw_zbhzData(d)).toString().replace("null", "0.00");
		return gdw_zbhz;
	}
	
	@RequestMapping(value = "gdw_zbhz.do", method = RequestMethod.GET)
	public ModelAndView getGdw_zbhz(HttpServletRequest request,
			HttpServletResponse response) {
//		Calendar now = Calendar.getInstance();  
//		int month = now.get(Calendar.MONTH) + 1;
//		int year = now.get(Calendar.YEAR);
//		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + now.get(Calendar.DAY_OF_MONTH));
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("month", month);
//		map.put("year", year);
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(service.getLatestGcyDate(), true, false);
		dateSel.select(map);
		return new ModelAndView("gdw_zbhz", map);
	}

	
	@RequestMapping(value = "xjlrb_update.do", method = RequestMethod.GET)
	public @ResponseBody String getXjlrb_update(HttpServletRequest request,
			HttpServletResponse response) {
//		int month = Integer.parseInt(request.getParameter("month"));
//		int year = Integer.parseInt(request.getParameter("year"));
//		int day = Integer.parseInt(request.getParameter("day"));
		Date d = DateSelection.getDate(request);
		String xjlrb = JSONArray.fromObject(service.getXjlrbData(d)).toString().replace("null", "0.00");
		return xjlrb;
	}
	
	@RequestMapping(value = "xjlrb.do", method = RequestMethod.GET)
	public ModelAndView getXjlrb(HttpServletRequest request,
			HttpServletResponse response) {
//		Calendar now = Calendar.getInstance();  
//		int month = now.get(Calendar.MONTH) + 1;
//		int year = now.get(Calendar.YEAR);
//		int day = now.get(Calendar.DAY_OF_MONTH);
//		int dayCount = now.getActualMaximum(Calendar.DAY_OF_MONTH);
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("month", month);
//		map.put("year", year);
//		map.put("day",  day);
//		map.put("dayCount",  dayCount);
		
		DateSelection dateSel = new DateSelection(service.getLatestXjlDate());
		dateSel.select(map);
		
		
		return new ModelAndView("xjlrb", map);
	}
	
	
//	@RequestMapping(value = "yszkrb_qkb.do", method = RequestMethod.GET)
//	public ModelAndView getYszkrb_qkb(HttpServletRequest request,
//			HttpServletResponse response) {
//		Calendar now = Calendar.getInstance();  
//		int month = now.get(Calendar.MONTH) + 1;
//		int year = now.get(Calendar.YEAR);
//		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + now.get(Calendar.DAY_OF_MONTH));
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("month", month);
//		map.put("year", year);
//		String yszkrb_qkb = JSONArray.fromObject(service.getYszkrb_qkbData(d)).toString().replace("null", "0.00");
//		map.put("yszkrb_qkb", yszkrb_qkb);
//		return new ModelAndView("yszkrb_qkb", map);
//	}
	
	
	private String getZbhz_overviewData(Date d, int companyId, String zbid){

		Organization org = CompanyManager.getOperationOrganization();
		Company comp = org.getCompany(CompanyType.valueOf(companyId));

		String zbhz_overview_yd = JSONArray.fromObject(service.getYdZbhz_overviewData(d, comp, zbid)).toString().replace("null", "0.00");
		String zbhz_overview_jd = JSONArray.fromObject(service.getJdZbhz_overviewData(d, comp, zbid)).toString().replace("null", "0.00");
		String zbhz_overview_nd = JSONArray.fromObject(service.getNdZbhz_overviewData(d, comp, zbid)).toString().replace("null", "0.00");
		String zbhz_overview_ydtb = JSONArray.fromObject(service.getYdtbZbhz_overviewData(d, comp, zbid)).toString().replace("null", "0.00");
		String zbhz_overview_jdtb = JSONArray.fromObject(service.getJdtbZbhz_overviewData(d, comp, zbid)).toString().replace("null", "0.00");
		
		return "{\"yd\":" + zbhz_overview_yd + ", \"jd\" : " + zbhz_overview_jd + ", \"nd\":"+ zbhz_overview_nd +" , \"ydtb\":"+ zbhz_overview_ydtb +", \"jdtb\":" + zbhz_overview_jdtb + "}";
	}
	
	@RequestMapping(value = "zbhz_overview_update.do", method = RequestMethod.GET)
	public @ResponseBody String updateZbhz_overview(HttpServletRequest request,
			HttpServletResponse response) {
		String companyId = request.getParameter("companyId");
		if (companyId == null){
			companyId = CompanyType.JT.ordinal() + "";
		}
		
		int cid = Integer.parseInt(companyId);
		String zb = request.getParameter("zb");
		if (zb == null){
			zb = "5";
		}
		
		Date d = service.getLatestGcyDate();
		return getZbhz_overviewData(d, cid, zb);
	}
	
	
	
	@RequestMapping(value = "zbhz_overview.do", method = RequestMethod.GET)
	public ModelAndView getZbhz_overview(HttpServletRequest request,
			HttpServletResponse response) {
		
		String zb = request.getParameter("zb");
		if (zb == null){
			zb = "5";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();

		DateSelection dateSel = new DateSelection(service.getLatestGcyDate(), true, false);
		dateSel.select(map);

		map.put("zbid", zb);
		map.put("zbmc", service.getZbmc(zb));
			
		Organization org = CompanyManager.getOperationZBHZOrganization();
		
		CompanySelection compSel = new CompanySelection(false, org.getTopCompany());
		compSel.select(map);
		
//		List<Company> coms = org.getBottomCompany();
//		coms.addAll(org.getCompany(CompanyType.JT).getSubCompanysWithLeaves());
//		coms.add(org.getCompany(CompanyType.JT));
		
//		for (int i = 0;  i < coms.size(); ++i){
//			map.put("id_" + i, i);
//			map.put("name_" + i, coms.get(i).getName());
//		}
		return new ModelAndView("zbhz_overview", map);
	}
}
