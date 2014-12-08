package com.tbea.test.testWebProject.controller.servlet.yszkpzjh;

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

import com.tbea.test.testWebProject.common.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.CompanyManager;
import com.tbea.test.testWebProject.common.Organization;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.service.yszkpzjh.YSZKPZJHService;

@Controller
@RequestMapping(value = "yszkpzjh")
public class YSZKPZJHController {

	@Autowired
	private YSZKPZJHService service;

	private String view = "yszkpzjh";

	
	@RequestMapping(value = "yszkpzjh_update.do", method = RequestMethod.GET)
	public @ResponseBody String getYszkpzjh_update(HttpServletRequest request,
			HttpServletResponse response) {
		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));
		String companyId = request.getParameter("companyId");
		int cid = Integer.parseInt(companyId);
		Date d = java.sql.Date.valueOf(year + "-" + month + "-" + 1);
		CompanyType compType = CompanyManager.getType(cid);
		Organization org = CompanyManager.getPzghOrganization();
		Company comp = org.getCompany(compType);	
		List<Company> comps = comp.getSubCompanys();
		
		YSZKPZJHBean[] yszkpzjhBeans = new YSZKPZJHBean[comps.size() + 1];
		yszkpzjhBeans[0] = service.getYszkpzjhData(d, comp);
		for (int i = 0; i < comps.size(); ++i){
			yszkpzjhBeans[i + 1] = service.getYszkpzjhData(d, comps.get(i));
		}
		String yszkpzjh = JSONArray.fromObject(yszkpzjhBeans).toString().replace("null", "0.00");
//		String yszkpzjh = "[]##[]##[]##[]";
//		if (!comps.isEmpty()){
//			
//			YSZKPZJHBean bean = service.getYszkpzjhData(d, comp);
//			
//			yszkpzjh = JSONArray.fromObject(bean.getList1()).toString().replace("null", "0.00");
//			yszkpzjh += "##" + JSONArray.fromObject(bean.getList2()).toString().replace("null", "0.00");
//			yszkpzjh += "##" + JSONArray.fromObject(bean.getList3()).toString().replace("null", "0.00");
//			yszkpzjh += "##" + JSONArray.fromObject(bean.getList4()).toString().replace("null", "0.00");
//		}
		
		return yszkpzjh;
	}
	

	
	@RequestMapping(value = "yszkpzjh.do", method = RequestMethod.GET)
	public ModelAndView getYszkpzjh(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		int month = now.get(Calendar.MONTH) + 1;
		int year = now.get(Calendar.YEAR);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		map.put("year", year);
		Organization org = CompanyManager.getPzghOrganization();
		String[][] name_ids = Util.getCompanyNameAndIds(org.getTopCompany());
		map.put("topComp", name_ids);
		List<String[][]> subComps = new ArrayList<String[][]>();
		for (int i = 0; i < org.getTopCompany().size(); ++i){
			name_ids = Util.getCompanyNameAndIds(org.getTopCompany().get(i).getSubCompanys());
			subComps.add(name_ids);
		}
		map.put("subComp", subComps);
		map.put("onlytop", true);
		return new ModelAndView("yszkpzjh", map);
	}
}
