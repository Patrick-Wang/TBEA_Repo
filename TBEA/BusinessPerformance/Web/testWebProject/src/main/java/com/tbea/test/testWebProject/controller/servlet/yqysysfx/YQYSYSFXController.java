package com.tbea.test.testWebProject.controller.servlet.yqysysfx;

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

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.CompanyManager;
import com.tbea.test.testWebProject.common.Organization;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.common.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.service.blht.BLHTService;
import com.tbea.test.testWebProject.service.yqysysfx.YQYSYSFXService;

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
			int month = Integer.parseInt(request.getParameter("month"));
			int year = Integer.parseInt(request.getParameter("year"));
			//int day = Integer.parseInt(request.getParameter("day"));
			String companyId = request.getParameter("companyId");
			int cid = Integer.parseInt(companyId);
			Organization org = CompanyManager.getOperationOrganization();
			Company comp = org.getCompany(CompanyType.valueOf(cid));
			Date d = java.sql.Date.valueOf(year + "-" + month + "-" + 1);
			String xjlrb = JSONArray.fromObject(service.getYqysysfxData(d, comp)).toString().replace("null", "0.00");
			return xjlrb;
		}	
	
	@RequestMapping(value = "yqysysfx.do", method = RequestMethod.GET)
	public ModelAndView getYqysysfx(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar now = Calendar.getInstance();  
		Map<String, Object> map = new HashMap<String, Object>();
		Organization org = CompanyManager.getOperationOrganization();
		String[][] name_ids = Util.getCompanyNameAndIds(org.getCompany(CompanyType.SBDCY).getSubCompanys());
		map.put("names", name_ids[0]);
		map.put("ids", name_ids[1]);
		map.put("company_size", name_ids[0].length);
		return new ModelAndView("yqysysfx", map);
	}

}
