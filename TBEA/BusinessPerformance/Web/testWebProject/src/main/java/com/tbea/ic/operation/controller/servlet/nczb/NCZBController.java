package com.tbea.ic.operation.controller.servlet.nczb;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import com.tbea.ic.operation.common.companys.BMDepartmentDB;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.common.companys.VirtualJYZBOrganization;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.controller.servlet.ydzb.CompanyTypeFilter;
import com.tbea.ic.operation.service.nczb.NCZBService;
import com.tbea.ic.operation.service.ydzb.YDZBService;
import com.tbea.ic.operation.service.ydzb.gszb.GszbService;


@Controller
@RequestMapping(value = "NCzb")
public class NCZBController {
	
	@Autowired
	private YDZBService service;
	
	@Autowired
	private GszbService gszbService;
	
	
	@Autowired
	private NCZBService nczbService;
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@RequestMapping(value = "AllCompanysNC_overview.do", method = RequestMethod.GET)
	public ModelAndView getAllCompanysNC_overview(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, false);
		dateSel.select(map);
		return new ModelAndView("hzbNC_zbhz", map);
	}
	
	@RequestMapping(value = "CompanysNC.do", method = RequestMethod.GET)
	public ModelAndView getCompanysNC(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, false);
		dateSel.select(map);
		Organization org = companyManager.getVirtualJYZBOrganization();
		CompanySelection compSel = new CompanySelection(
				false,
				org.getCompany(CompanyType.GFGS).getSubCompanies(), 
				new CompanyTypeFilter(
						gszbService.getCompanies(SessionManager.getAccount(request.getSession(false))), 
						org));

		compSel.select(map, 3);
		return new ModelAndView("hzb_companysNC", map);
	}
	
	private List<String[]> removeJydwzb(List<String[]> data){
		for (int i = 0; i < data.size(); ++i){
			if ("净资产收益率(%)".equals(data.get(i)[0])){
				data.remove(i);
				--i;
			} else if("负债率".equals(data.get(i)[0])){
				data.remove(i);
				--i;
			}
		}
		return data;
	}
	
	
	@RequestMapping(value = "AllCompanysNC_overview_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getAllCompanysNC_overview_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = DateSelection.getDate(request);
		List<String[]> ncGszbData = nczbService.getGSZB(d, BMDepartmentDB.getJydw(companyManager));
		JSONArray ja = JSONArray.fromObject(removeJydwzb(ncGszbData));
		return ja.toString().replace("null", "\"--\"").getBytes("utf-8");
	}
	
	@RequestMapping(value = "CompanysNC_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getCompanysNC_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = DateSelection.getDate(request);
		CompanyType cm = CompanyType.valueOf(Integer.valueOf(request.getParameter("companyId")));
		List<String[]> ncGszbData = nczbService.getGSZB(d, VirtualJYZBOrganization.getJydw(companyManager, cm));
		if (VirtualJYZBOrganization.isSbdcy(cm) || VirtualJYZBOrganization.isSyb(cm)){
			ncGszbData = removeJydwzb(ncGszbData);
		}
		JSONArray ja = JSONArray.fromObject(ncGszbData);
		return ja.toString().replace("null", "\"--\"").getBytes("utf-8");
	}

}
