package com.tbea.ic.operation.controller.servlet.yqkbhqs;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
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
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.yqkbhqs.YQKBHQSService;

@Controller
@RequestMapping(value = "yqkbhqs")
public class YQKBHQSController {
	@Autowired
	private YQKBHQSService service;
	private String view = "yqkqsbh";
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@RequestMapping(value = "yqkbhqs_update.do", method = RequestMethod.GET)
	public @ResponseBody String getYqkbhqs_update(HttpServletRequest request,
			HttpServletResponse response) {
//		int year = Integer.parseInt(request.getParameter("year"));
//		String companyId = request.getParameter("companyId");
//		int cid = Integer.parseInt(companyId);
//		Date d = java.sql.Date.valueOf(year + "-" + 1 + "-" + 1);
//
//		Organization org = companyManager.getOperationOrganization();
//		Company comp = org.getCompany(CompanyType.valueOf(cid));
		
		Date d = DateSelection.getDate(request);
		Organization org = companyManager.getOperationOrganization();
		Company comp = org.getCompany(CompanySelection.getCompany(request));

		String yqkbhqs = JSONArray.fromObject(service.getYqkbhqsData(d, comp)).toString().replace("null", "0.00");
		return yqkbhqs;
	}

	
	@RequestMapping(value = "yqkbhqs.do", method = RequestMethod.GET)
	public ModelAndView getYqkbhqs(HttpServletRequest request,
			HttpServletResponse response) {
//		Calendar now = Calendar.getInstance();  
//		Date d = service.getLatestDate();
//		if (null != d){
//			now.setTime(d);
//		}
//		int year = now.get(Calendar.YEAR);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("year", year);
//		Organization org = companyManager.getOperationOrganization();
//		String[][] name_ids = Util.getCompanyNameAndIds(org.getCompany(CompanyType.SBDCY).getSubCompanys());
//		map.put("names", name_ids[0]);
//		map.put("ids", name_ids[1]);
//		map.put("company_size", name_ids[0].length);
		
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(service.getLatestDate(), false, false);
		dateSel.select(map);

		Organization org = companyManager.getOperationOrganization();
		CompanySelection compSel = new CompanySelection(true, org.getCompany(CompanyType.SBDCY).getSubCompanys());
		compSel.select(map);
		return new ModelAndView(view, map);
	}

	
}
