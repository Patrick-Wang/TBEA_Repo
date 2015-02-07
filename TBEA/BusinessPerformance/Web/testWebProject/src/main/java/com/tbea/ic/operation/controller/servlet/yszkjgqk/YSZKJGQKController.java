package com.tbea.ic.operation.controller.servlet.yszkjgqk;

import java.sql.Date;
import java.util.ArrayList;
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
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.yszkjgqk.YSZKJGQKService;

@Controller
@RequestMapping(value = "yszkjgqk")
public class YSZKJGQKController {
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	private YSZKJGQKService service;
	private String view = "yszkjgqkb";
	
	
	@RequestMapping(value = "yszkjgqk_update.do", method = RequestMethod.GET)
	public @ResponseBody String getYqkbhqs_update(HttpServletRequest request,
			HttpServletResponse response) {
		
		Date d = DateSelection.getDate(request);
		CompanyType compType = CompanySelection.getCompany(request);
		Company comp = companyManager.getBMOrganization().getCompany(compType);
		List<String[][]> result = new ArrayList<String[][]>();
		if (null == comp) {
			comp = companyManager.getVirtualYSZKOrganization().getCompany(compType);
			if (null != comp) {
				List<Company> comps = comp.getSubCompanys();
				result.add(service.getYszkjg(d, comps));
				result.add(service.getWdqtbbh(d, comps));
				result.add(service.getJetbbh(d, comps));
			}
		}
		else {
			result.add(service.getYszkjg(d, comp));
			result.add(service.getWdqtbbh(d, comp));
			result.add(service.getJetbbh(d, comp));
		}
		
		String jsonRet = JSONArray.fromObject(result).toString().replace("null", "0.00");
		return jsonRet;
	}
	
	@RequestMapping(value = "yszkjgqk.do", method = RequestMethod.GET)
	public ModelAndView getYqkbhqs(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();

		DateSelection dateSel = new DateSelection(service.getLatestDate(), true, false);
		dateSel.select(map);
		
		List<Company> comps = new ArrayList<Company>();
		comps.addAll(companyManager.getBMOrganization().getTopCompany());
		comps.addAll(companyManager.getVirtualYSZKOrganization().getTopCompany());
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);

		return new ModelAndView(view, map);
	}

	
}
