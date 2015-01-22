package com.tbea.test.testWebProject.controller.servlet.syhkjhzxqk;

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

import com.tbea.test.testWebProject.common.CompanySelection;
import com.tbea.test.testWebProject.common.DateSelection;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.common.companys.CompanyManager;
import com.tbea.test.testWebProject.common.companys.Organization;
import com.tbea.test.testWebProject.common.companys.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.service.cqk.CQKService;
import com.tbea.test.testWebProject.service.hkjhjg.HKJHJGService;
import com.tbea.test.testWebProject.service.syhkjhzxqk.SYHKJHZXQKService;

@Controller
@RequestMapping(value = "syhkjhzxqk")
public class SYHKJHZXQKController {

	@Autowired
	private SYHKJHZXQKService service;
	
	
	@RequestMapping(value = "syhkjhzxqk_update.do", method = RequestMethod.GET)
	public @ResponseBody String getSyhkjhzxqk_update(HttpServletRequest request,
			HttpServletResponse response) {
//		int month = Integer.parseInt(request.getParameter("month"));
//		int year = Integer.parseInt(request.getParameter("year"));
//		String companyId = request.getParameter("companyId");
//		int cid = Integer.parseInt(companyId);
//		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + 1);
//		
//		Organization org = CompanyManager.getOperationOrganization();
//		Company comp = org.getCompany(CompanyType.valueOf(cid));

		Date d = DateSelection.getDate(request);
		Organization org = CompanyManager.getOperationOrganization();
		Company comp = org.getCompany(CompanySelection.getCompany(request));
		
		
				
		List<String[][]> hkjhs = new ArrayList<String[][]>();
		hkjhs.add(service.getSyhkjhzxqkData(d, comp));
		hkjhs.add(service.getHkjhzxqkXjData(d, comp));
		String syhkjhzxqk = JSONArray.fromObject(hkjhs).toString().replace("null", "0.00");

		return syhkjhzxqk;
	}
	

	
	@RequestMapping(value = "syhkjhzxqk.do", method = RequestMethod.GET)
	public ModelAndView getSyhkjhzxqk(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(service.getLatestDate(), true, false);
		dateSel.select(map);

		Organization org = CompanyManager.getOperationOrganization();
		CompanySelection compSel = new CompanySelection(true, org.getCompany(CompanyType.SBDCY).getSubCompanys());
		compSel.setFirstCompany(CompanyType.HB);
		compSel.select(map);
		
		return new ModelAndView("syhkjhzxqk", map);
	}
}
