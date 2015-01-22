package com.tbea.test.testWebProject.controller.servlet.yszkjgqk;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.tbea.test.testWebProject.service.yqkbhqs.YQKBHQSService;
import com.tbea.test.testWebProject.service.yszkjgqk.YSZKJGQKService;

@Controller
@RequestMapping(value = "yszkjgqk")
public class YSZKJGQKController {
	@Autowired
	private YSZKJGQKService service;
	private String view = "yszkjgqkb";
	
	
	@RequestMapping(value = "yszkjgqk_update.do", method = RequestMethod.GET)
	public @ResponseBody String getYqkbhqs_update(HttpServletRequest request,
			HttpServletResponse response) {
//		int month = Integer.parseInt(request.getParameter("month"));
//		int year = Integer.parseInt(request.getParameter("year"));
//		String companyId = request.getParameter("companyId");
//		int cid = Integer.parseInt(companyId);
//		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + 1);
//		Organization org = CompanyManager.getBMOrganization();
//		Company comp = org.getCompany(CompanyType.valueOf(cid));
		
		
		Date d = DateSelection.getDate(request);
		Organization org = CompanyManager.getBMOrganization();
		Company comp = org.getCompany(CompanySelection.getCompany(request));
		
		List<String[][]> result = new ArrayList<String[][]>();
		result.add(service.getYszkjg(d, comp));
		result.add(service.getWdqtbbh(d, comp));
		result.add(service.getJetbbh(d, comp));
		
		String jsonRet = JSONArray.fromObject(result).toString().replace("null", "0.00");
		
		return jsonRet;
	}
	
	@RequestMapping(value = "yszkjgqk.do", method = RequestMethod.GET)
	public ModelAndView getYqkbhqs(HttpServletRequest request,
			HttpServletResponse response) {
//		Calendar date = Calendar.getInstance();  
//		Date d = service.getLatestDate();
//		if (null != d){
//			date.setTime(d);
//		}
//		
//		int month = date.get(Calendar.MONTH) + 1;
//		int year = date.get(Calendar.YEAR);

		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("month", month);
//		map.put("year", year);
		
		DateSelection dateSel = new DateSelection(service.getLatestDate(), true, false);
		dateSel.select(map);
		
		
		//Organization org = CompanyManager.getBMOrganization();
		
		Organization org = CompanyManager.getBMOrganization();
		CompanySelection compSel = new CompanySelection(true, org.getTopCompany());
		compSel.select(map);

		
//		
//		List<Company> comps = org.getTopCompany();
//		List<Company> existComps = new ArrayList<Company>();
//		for (int i = 0; i < comps.size(); ++i){
//			if (service.IsCompanyExist(comps.get(i))){
//				existComps.add(comps.get(i));
//			}
//		}
		
//		String[][] name_ids = Util.getCompanyNameAndIds(comps);
//		
//		map.put("topComp", name_ids);
		//map.put("topFirst", name_ids);
//		List<String[][]> subComps = new ArrayList<String[][]>();
//		for (int i = 0; i < org.getTopCompany().size(); ++i){
//			name_ids = Util.getCompanyNameAndIds(org.getTopCompany().get(i).getSubCompanys());
//			subComps.add(name_ids);
//		}
//		map.put("subComp", subComps);
//		map.put("onlytop", true);
//		map.put("both", false);
		
//		Organization org = CompanyManager.getOperationOrganization();
//		String[][] name_ids = Util.getCompanyNameAndIds(org.getCompany(CompanyType.SBDCY).getSubCompanys());
//		map.put("names", name_ids[0]);
//		map.put("ids", name_ids[1]);
//		map.put("company_size", name_ids[0].length);
		return new ModelAndView(view, map);
	}

	
}
