package com.tbea.ic.operation.controller.servlet.yqysysfx;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
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

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.yqysysfx.YQYSYSFXService;

@Controller
@RequestMapping(value = "yqysysfx")
public class YQYSYSFXController {

	@Autowired
	private YQYSYSFXService service;

	private String view = "blhtPage";

	private String commandName = "result";

	@RequestMapping(value = "yqysysfx_update.do", method = RequestMethod.GET)
	public @ResponseBody String getYqysysfx_update(HttpServletRequest request,
			HttpServletResponse response) {
		// String companyId = request.getParameter("companyId");
		// int cid = Integer.parseInt(companyId);
		// Organization org = CompanyManager.getOperationOrganization();
		// Company comp = org.getCompany(CompanyType.valueOf(cid));

		Organization org = CompanyManager.getOperationOrganization();
		Company comp = org.getCompany(CompanySelection.getCompany(request));

		String xjlrb = JSONArray.fromObject(service.getYqysysfxData(comp))
				.toString().replace("null", "0.00");
		return xjlrb;
	}

	@RequestMapping(value = "yqysysfx.do", method = RequestMethod.GET)
	public ModelAndView getYqysysfx(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		Organization org = CompanyManager.getOperationOrganization();
		CompanySelection compSel = new CompanySelection(true, org.getCompany(
				CompanyType.SBDCY).getSubCompanys());
		compSel.select(map);

		// Calendar now = Calendar.getInstance();
		//
		// Organization org = CompanyManager.getOperationOrganization();
		// String[][] name_ids =
		// Util.getCompanyNameAndIds(org.getCompany(CompanyType.SBDCY).getSubCompanys());
		// map.put("names", name_ids[0]);
		// map.put("ids", name_ids[1]);
		// //map.put("all", CompanyType.SBDCY.ordinal() + "");
		// map.put("company_size", name_ids[0].length);
		return new ModelAndView("yqysysfx", map);
	}

}
