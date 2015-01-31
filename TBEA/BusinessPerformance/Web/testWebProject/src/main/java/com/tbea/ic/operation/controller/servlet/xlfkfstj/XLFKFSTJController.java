package com.tbea.ic.operation.controller.servlet.xlfkfstj;

import java.io.UnsupportedEncodingException;
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
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.xlfkfstj.XLFKFSTJService;

@Controller
@RequestMapping(value = "xlfkfstj")
public class XLFKFSTJController {

	@Autowired
	private XLFKFSTJService service;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@RequestMapping(value = "xlfkfstj_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getZtyszkfx_update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = DateSelection.getDate(request);
//		Organization org = companyManager.getBMOrganization();
//		Company comp = org.getCompany(CompanySelection.getCompany(request));
		
		
		
		
		
		CompanyType compType = CompanySelection.getCompany(request);
		Company comp = companyManager.getBMOrganization().getCompany(compType);
		List<String[][]> result = new ArrayList<String[][]>();
		if (null == comp) {
			comp = companyManager.getVirtualYSZKOrganization().getCompany(compType);
			if (null != comp) {
				List<Company> comps = comp.getSubCompanys();
				result.add(service.getFdwData(d, comps));
				result.add(service.getGwData(d, comps));
				result.add(service.getNwData(d, comps));
			}
		}
		else {
			result.add(service.getFdwData(d, comp));
			result.add(service.getGwData(d, comp));
			result.add(service.getNwData(d, comp));
		}

		return JSONArray.fromObject(result).toString().replace("null", "0.00").getBytes("utf-8");
	}
	
	@RequestMapping(value = "xlfkfstj.do", method = RequestMethod.GET)
	public ModelAndView getZtyszkfx(HttpServletRequest request,
			HttpServletResponse response) {
			
		Map<String, Object> map = new HashMap<String, Object>();

		List<Company> comps = new ArrayList<Company>();
		comps.addAll(companyManager.getBMOrganization().getTopCompany());
		comps.addAll(companyManager.getVirtualYSZKOrganization().getTopCompany());

		CompanySelection compSelection = new CompanySelection(true,
				comps, new CompanySelection.Filter() {
					@Override
					public boolean keep(Company comp) {
						if (null != companyManager.getVirtualYSZKOrganization().getCompany(comp.getType())){
							List<Company> subComps = comp.getSubCompanys();
							for (Company com : subComps){
								if (service.containsCompany(com)){
									return true;
								}
							}
							return false;
						}
						else{
							return service.containsCompany(comp);
						}
					}
				});
		compSelection.select(map);

		DateSelection dateSel = new DateSelection(service.getLatestDate(), true, false);
		dateSel.select(map);
		
		return new ModelAndView("xl_fkfstj", map);
	}
}
