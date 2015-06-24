package com.tbea.ic.operation.controller.servlet.yqkbhqs;

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
import com.tbea.ic.operation.common.companys.CompanyType;
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
		
		Date d = DateSelection.getDate(request);


		String yqkbhqs = null;

		CompanyType compType = CompanySelection.getCompany(request);
		Company comp = companyManager.getOperationOrganization().getCompany(compType);
		if (null == comp) {
			comp = companyManager.getVirtualYSZKOrganization().getCompany(compType);
			if (null != comp) {
				yqkbhqs = JSONArray.fromObject(service.getYqkbhqsData(d, comp.getSubCompanies()))
						.toString().replace("null", "0.00");
			}
		}
		else {
			yqkbhqs = JSONArray.fromObject(service.getYqkbhqsData(d, comp))
					.toString().replace("null", "0.00");
		}
		
		
		return yqkbhqs;
	}

	
	@RequestMapping(value = "yqkbhqs.do", method = RequestMethod.GET)
	public ModelAndView getYqkbhqs(HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(service.getLatestDate(), false, false);
		dateSel.select(map);

		List<Company> comps = new ArrayList<Company>();
		comps.addAll(companyManager.getOperationOrganization().getCompany(CompanyType.SBDCY).getSubCompanies());
		comps.addAll(companyManager.getVirtualYSZKOrganization().getTopCompany());
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		return new ModelAndView(view, map);
	}

	
}
