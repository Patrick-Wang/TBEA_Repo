package com.tbea.ic.operation.controller.servlet.yszkpzjh;

import java.sql.Date;
import java.util.ArrayList;
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
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.yszkpzjh.YSZKPZJHService;

@Controller
@RequestMapping(value = "yszkpzjh")
public class YSZKPZJHController {

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	private YSZKPZJHService service;

	private String view = "yszkpzjh";

	
	@RequestMapping(value = "yszkpzjh_update.do", method = RequestMethod.GET)
	public @ResponseBody String getYszkpzjh_update(HttpServletRequest request,
			HttpServletResponse response) {

		Date d = DateSelection.getDate(request);
		Organization org = companyManager.getPzghOrganization();
		Company comp = org.getCompany(CompanySelection.getCompany(request));
		
		
		List<Company> comps = comp.getSubCompanys();
		
		YSZKPZJHBean[] yszkpzjhBeans = new YSZKPZJHBean[comps.size() + 1];
		yszkpzjhBeans[0] = service.getYszkpzjhData(d, comp);
		for (int i = 0; i < comps.size(); ++i){
			yszkpzjhBeans[i + 1] = service.getYszkpzjhData(d, comps.get(i));
		}
		String yszkpzjh = JSONArray.fromObject(yszkpzjhBeans).toString().replace("null", "0.00");
		
		return yszkpzjh;
	}
	

	
	@RequestMapping(value = "yszkpzjh.do", method = RequestMethod.GET)
	public ModelAndView getYszkpzjh(HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
//		Date date = service.getLatestDate();
//		Calendar now = Calendar.getInstance();  
//		if (null != date){
//			now.setTime(date);
//		}
//		
//		int month = now.get(Calendar.MONTH) + 1;
//		int year = now.get(Calendar.YEAR);
//		
//		map.put("month", month);
//		map.put("year", year);
		
		DateSelection dateSel = new DateSelection(service.getLatestDate(), true, false);
		dateSel.select(map);
		
		
		Organization org = companyManager.getPzghOrganization();
		CompanySelection compSelection = new CompanySelection(true,
				org.getTopCompany());
		compSelection.setFirstCompany(CompanyType.HB);
		compSelection.select(map);
		
		
//		String[][] name_ids = Util.getCompanyNameAndIds(org.getTopCompany());
//		map.put("topComp", name_ids);
//		List<String[][]> subComps = new ArrayList<String[][]>();
//		for (int i = 0; i < org.getTopCompany().size(); ++i){
//			name_ids = Util.getCompanyNameAndIds(org.getTopCompany().get(i).getSubCompanys());
//			subComps.add(name_ids);
//		}
//		map.put("subComp", subComps);
//		map.put("onlytop", true);
//		map.put("firstCompany", CompanyType.HB.ordinal());
		return new ModelAndView("yszkpzjh", map);
	}
}
