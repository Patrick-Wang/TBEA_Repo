package com.tbea.test.testWebProject.controller.servlet.cb;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
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
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.common.companys.CompanyManager;
import com.tbea.test.testWebProject.common.companys.Organization;
import com.tbea.test.testWebProject.common.companys.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.service.cb.BYQCBService;

@Controller
@RequestMapping(value = "byqcb")
public class BYQCBController {
	@Autowired
	private BYQCBService service;

	@RequestMapping(value = "tb_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getByqtbcb_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		String companyId = request.getParameter("companyId");
		int cid = Integer.parseInt(companyId);
		Organization org = CompanyManager.getBMOrganization();
		Company comp = org.getCompany(CompanyType.valueOf(cid));

		String[][] aTbmx = service.getTbmx(
				Date.valueOf(year + "-" + month + "-1"), comp);
		String tbmx = JSONArray.fromObject(aTbmx).toString()
				.replace("null", "0.00");

		return tbmx.getBytes("utf-8");
	}

	@RequestMapping(value = "tb.do", method = RequestMethod.GET)
	public ModelAndView getByqtbcb(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		List<String[][]> tbs = service.getTbmx(Date.valueOf(year + "-" + month
				+ "-1"));
		String[][] aTbmx = tbs.get(0);
		String[][] aJttb = tbs.get(1);
		String[][] aGstb = tbs.get(2);
		Map<String, Object> map = new HashMap<String, Object>();
		String tbmx = JSONArray.fromObject(aTbmx).toString()
				.replace("null", "0.00");
		String jttb = JSONArray.fromObject(aJttb).toString()
				.replace("null", "0.00");
		String gstb = JSONArray.fromObject(aGstb).toString()
				.replace("null", "0.00");
		map.put("tbmx", tbmx);
		map.put("jttb", jttb);
		map.put("gstb", gstb);
		map.put("month", month);

		Organization org = CompanyManager.getBMOrganization();
		CompanySelection compSelection = new CompanySelection(false,
				org.getTopCompany(), new CompanySelection.Filter() {
					@Override
					public boolean keep(Company comp) {
						return service.IsTbCompanyExist(comp);
					}
				});
		compSelection.select(map);

		return new ModelAndView("cb_byq", map);
	}

	@RequestMapping(value = "zx_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getByqzxcb_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		String companyId = request.getParameter("companyId");
		int cid = Integer.parseInt(companyId);
		Organization org = CompanyManager.getBMOrganization();
		Company comp = org.getCompany(CompanyType.valueOf(cid));

		String[][] aZxmx = service.getZxmx(
				Date.valueOf(year + "-" + month + "-1"), comp);
		String zxmx = JSONArray.fromObject(aZxmx).toString()
				.replace("null", "0.00");

		return zxmx.getBytes("utf-8");
	}

	@RequestMapping(value = "zx.do", method = RequestMethod.GET)
	public ModelAndView getByqzxcb(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		List<String[][]> zxs = service.getZxmx(Date.valueOf(year + "-" + month
				+ "-1"));
		String[][] aZxmx = zxs.get(0);
		String[][] aJtzx = zxs.get(1);
		String[][] aGszx = zxs.get(2);
		Map<String, Object> map = new HashMap<String, Object>();
		String zxmx = JSONArray.fromObject(aZxmx).toString()
				.replace("null", "0.00");
		String jtzx = JSONArray.fromObject(aJtzx).toString()
				.replace("null", "0.00");
		String gszx = JSONArray.fromObject(aGszx).toString()
				.replace("null", "0.00");
		map.put("zxmx", zxmx);
		map.put("jtzx", jtzx);
		map.put("gszx", gszx);
		map.put("month", month);

		Organization org = CompanyManager.getBMOrganization();
		CompanySelection compSelection = new CompanySelection(false,
				org.getTopCompany(), new CompanySelection.Filter() {
					@Override
					public boolean keep(Company comp) {
						return service.IsZxCompanyExist(comp);
					}
				});
		compSelection.select(map);

		return new ModelAndView("cb_zx_byq", map);
	}

	@RequestMapping(value = "wg_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getByqwgcb_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		String companyId = request.getParameter("companyId");
		int cid = Integer.parseInt(companyId);
		Organization org = CompanyManager.getBMOrganization();
		Company comp = org.getCompany(CompanyType.valueOf(cid));

		String[][] aWgmx = service.getWgmx(
				Date.valueOf(year + "-" + month + "-1"), comp);
		String wgmx = JSONArray.fromObject(aWgmx).toString()
				.replace("null", "0.00");

		return wgmx.getBytes("utf-8");

	}
	
	@RequestMapping(value = "wg_date_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getByqwgcb_date_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		int month = Integer.parseInt(request.getParameter("month"));
		int year = Integer.parseInt(request.getParameter("year"));
		List<String[][]> aWgs = service.getJtwg(Date.valueOf(year + "-" + month + "-1"));
		String wgs = JSONArray.fromObject(aWgs).toString()
				.replace("null", "0.00");

		return wgs.getBytes("utf-8");

	}

	@RequestMapping(value = "wg.do", method = RequestMethod.GET)
	public ModelAndView getByqwgcb(HttpServletRequest request,
			HttpServletResponse response) {
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		List<String[][]> wgs = service.getWgmx(Date.valueOf(year + "-" + month
				+ "-1"));
		String[][] aWgmx = wgs.get(0);
		String[][] aJtwg = wgs.get(1);
		String[][] aGswg = wgs.get(2);
		String[][] aBtdywg = wgs.get(3);
		Map<String, Object> map = new HashMap<String, Object>();
		String wgmx = JSONArray.fromObject(aWgmx).toString()
				.replace("null", "0.00");
		String jtwg = JSONArray.fromObject(aJtwg).toString()
				.replace("null", "0.00");
		String gswg = JSONArray.fromObject(aGswg).toString()
				.replace("null", "0.00");
		String btdywg = JSONArray.fromObject(aBtdywg).toString()
				.replace("null", "0.00");
		map.put("wgmx", wgmx);
		map.put("jtwg", jtwg);
		map.put("gswg", gswg);
		map.put("btdywg", btdywg);

		Organization org = CompanyManager.getBMOrganization();
		CompanySelection compSelection = new CompanySelection(false,
				org.getTopCompany(), new CompanySelection.Filter() {
					@Override
					public boolean keep(Company comp) {
						return service.IsWgCompanyExist(comp);
					}
				});
		compSelection.select(map);

		map.put("year", year);
		map.put("month", month);
		
		return new ModelAndView("cb_wg_byq", map);
	}
}
