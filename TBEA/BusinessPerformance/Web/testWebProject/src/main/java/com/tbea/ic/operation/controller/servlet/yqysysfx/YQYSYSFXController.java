package com.tbea.ic.operation.controller.servlet.yqysysfx;

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
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.yqysysfx.YQYSYSFXService;

@Controller
@RequestMapping(value = "yqysysfx")
public class YQYSYSFXController {

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	private YQYSYSFXService service;

	@RequestMapping(value = "yqysysfx_update.do", method = RequestMethod.GET)
	public @ResponseBody String getYqysysfx_update(HttpServletRequest request,
			HttpServletResponse response) {

		Date d = DateSelection.getDate(request);
		
		String xjlrb = null;
		CompanyType compType = CompanySelection.getCompany(request);
		Company comp = companyManager.getOperationOrganization().getCompany(compType);
		if (null == comp) {
			comp = companyManager.getVirtualYSZKOrganization().getCompany(compType);
			if (null != comp) {
				xjlrb = JSONArray.fromObject(service.getYqysysfxData(d, comp.getSubCompanys()))
						.toString().replace("null", "0.00");
			}
		}
		else {
			xjlrb = JSONArray.fromObject(service.getYqysysfxData(d, comp))
					.toString().replace("null", "0.00");
		}

		return xjlrb;
	}

	@RequestMapping(value = "yqysysfx.do", method = RequestMethod.GET)
	public ModelAndView getYqysysfx(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Company> comps = new ArrayList<Company>();
		comps.addAll(companyManager.getOperationOrganization().getCompany(CompanyType.SBDCY).getSubCompanys());
		comps.addAll(companyManager.getVirtualYSZKOrganization().getTopCompany());
		CompanySelection compSel = new CompanySelection(true, comps);
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		compSel.select(map);
		return new ModelAndView("yqysysfx", map);
	}

}
