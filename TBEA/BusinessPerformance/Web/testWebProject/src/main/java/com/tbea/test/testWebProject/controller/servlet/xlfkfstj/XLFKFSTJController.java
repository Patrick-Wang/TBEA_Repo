package com.tbea.test.testWebProject.controller.servlet.xlfkfstj;

import java.io.UnsupportedEncodingException;
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
import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.common.companys.CompanyManager;
import com.tbea.test.testWebProject.common.companys.Organization;
import com.tbea.test.testWebProject.common.companys.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.service.xlfkfstj.XLFKFSTJService;

@Controller
@RequestMapping(value = "xlfkfstj")
public class XLFKFSTJController {

	@Autowired
	private XLFKFSTJService service;
	
	
	@RequestMapping(value = "xlfkfstj_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getZtyszkfx_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));
		Date d = java.sql.Date.valueOf(year + "-" +  month + "-1");
		
		String companyId = request.getParameter("companyId");
		int cid = Integer.parseInt(companyId);
		Organization org = CompanyManager.getBMOrganization();
		Company comp = org.getCompany(CompanyType.valueOf(cid));
		
		
		List<String[][]> result=  new ArrayList<String[][]>();
		result.add(service.getFdwData(d, comp));
		result.add(service.getGwData(d, comp));
		result.add(service.getNwData(d, comp));
		return JSONArray.fromObject(result).toString().replace("null", "0.00").getBytes("utf-8");
	}
	
	@RequestMapping(value = "xlfkfstj.do", method = RequestMethod.GET)
	public ModelAndView getZtyszkfx(HttpServletRequest request,
			HttpServletResponse response) {
			
		Map<String, Object> map = new HashMap<String, Object>();
	
		Organization org = CompanyManager.getBMOrganization();
		CompanySelection compSelection = new CompanySelection(true,
				org.getTopCompany(), new CompanySelection.Filter() {
					@Override
					public boolean keep(Company comp) {
						return service.containsCompany(comp);
					}
				});
		compSelection.select(map);
		
		Calendar date = Calendar.getInstance();  
		Date d = service.getLatestDate();
		if (null != d){
			date.setTime(d);
			date.add(Calendar.MONTH, -1);
		}
		
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		map.put("month", month);
		map.put("year", year);
		
		return new ModelAndView("xl_fkfstj", map);
	}
}
