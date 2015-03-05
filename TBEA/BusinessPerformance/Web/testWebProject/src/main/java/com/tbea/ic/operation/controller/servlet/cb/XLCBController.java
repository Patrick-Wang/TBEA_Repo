package com.tbea.ic.operation.controller.servlet.cb;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
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
import com.tbea.ic.operation.service.cb.XLCBService;

@Controller
@RequestMapping(value = "xlcb")
public class XLCBController {
	@Autowired
	private XLCBService service;

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@RequestMapping(value = "tb_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getXltbcb_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Organization org = companyManager.getBMOrganization();
		Company comp = org.getCompany(CompanySelection.getCompany(request));

		List<String[][]> aZxmx = service.getTbmx(
				DateSelection.getDate(request), comp);
		String zxmx = JSONArray.fromObject(aZxmx).toString()
				.replace("null", "0.00");

		return zxmx.getBytes("utf-8");
	}

	@RequestMapping(value = "tb.do", method = RequestMethod.GET)
	public ModelAndView getZbcb(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();

		DateSelection dateSel = new DateSelection(service.getLatestTbDate(),
				true, false);
		dateSel.select(map);

		Organization org = companyManager.getBMOrganization();
		CompanySelection compSelection = new CompanySelection(false,
				org.getTopCompany(), new CompanySelection.Filter() {
					private List<Integer> comps = service.getTbCompany();

					@Override
					public boolean keep(Company comp) {
						for (int i = 0; i < comps.size(); ++i) {
							if (comps.get(i) == comp.getId()) {
								return true;
							}
						}
						return false;
					}
					
					@Override
					public boolean keepGroup(Company comp) {
						// TODO Auto-generated method stub
						return true;
					}
				});
		compSelection.select(map);

		return new ModelAndView("cb_xl", map);
	}

	@RequestMapping(value = "wg_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getxlwgcb_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Organization org = companyManager.getBMOrganization();
		List<String[][]> wgs = service.getWgmx(DateSelection.getDate(request),
				org.getCompany(CompanySelection.getCompany(request)));

		return JSONArray.fromObject(wgs).toString().replace("null", "0.00")
				.getBytes("utf-8");

	}

	@RequestMapping(value = "wg.do", method = RequestMethod.GET)
	public ModelAndView getxlwgcb(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(service.getLatestWgDate(),
				true, false);
		dateSel.select(map);

		Organization org = companyManager.getBMOrganization();
		CompanySelection compSelection = new CompanySelection(false,
				org.getTopCompany(), new CompanySelection.Filter() {
					private List<Integer> comps = service.getWgCompany();

					@Override
					public boolean keep(Company comp) {
						for (int i = 0; i < comps.size(); ++i) {
							if (comps.get(i) == comp.getId()) {
								return true;
							}
						}
						return false;
					}
					
					@Override
					public boolean keepGroup(Company comp) {
						// TODO Auto-generated method stub
						return true;
					}
				});
		compSelection.select(map);

		return new ModelAndView("cb_wg_xl", map);
	}
}
