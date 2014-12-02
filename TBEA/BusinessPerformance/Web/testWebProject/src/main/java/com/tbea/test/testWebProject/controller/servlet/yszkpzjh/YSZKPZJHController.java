package com.tbea.test.testWebProject.controller.servlet.yszkpzjh;

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
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.service.cqk.CQKService;
import com.tbea.test.testWebProject.service.hkjhjg.HKJHJGService;
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
		
		Company comp = Company.get(cid);
		YSZKPZJHBean bean = service.getYszkpzjhData(d, comp);
		String yszkpzjh = JSONArray.fromObject(bean.getList1()).toString().replace("null", "0.00");
		yszkpzjh += "##" + JSONArray.fromObject(bean.getList2()).toString().replace("null", "0.00");
		yszkpzjh += "##" + JSONArray.fromObject(bean.getList3()).toString().replace("null", "0.00");
		yszkpzjh += "##" + JSONArray.fromObject(bean.getList4()).toString().replace("null", "0.00");
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
		String[][] name_ids = Util
				.getCommonCompanyNameAndIds(new Company.Type[] {
						Company.Type.TB, Company.Type.LL, Company.Type.XL,
						Company.Type.DL, Company.Type.XNY, Company.Type.GY,
						Company.Type.TCNY, Company.Type.NDGS,
						Company.Type.ZJWL, Company.Type.JCK, Company.Type.GCGS,
						Company.Type.ZH, Company.Type.SBDCY,
						Company.Type.XNYCY, Company.Type.NYCY,
						Company.Type.GCL, Company.Type.JT });
		map.put("names", name_ids[0]);
		map.put("ids", name_ids[1]);
		map.put("company_size", name_ids[0].length);
		return new ModelAndView("yszkpzjh", map);
	}
}
