package com.tbea.ic.operation.controller.servlet.cpzlqk;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.CompanySelection.Filter;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.EasyList;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;

@Controller
@RequestMapping(value = "cpzlqk")
public class CpzlqkServlet {

	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	
	private CompanySelection selectCompany(List<Company> comps){
		Organization org = companyManager.getVirtualCYOrg();
		List<Company> byqs = new EasyList<Company>(org.getCompany(CompanyType.BYQCY).getSubCompanies()).clone();
		List<Company> xls = new EasyList<Company>(org.getCompany(CompanyType.XLCY).getSubCompanies()).clone();
		List<Company> pds = new EasyList<Company>(org.getCompany(CompanyType.PDCY).getSubCompanies()).clone();

		CompanySelection compSel = new CompanySelection(false, org.getTopCompany(), new Filter(){

			@Override
			public boolean keep(Company comp) {
				for (Company cp : comps){
					if (cp.getType() == comp.getType()){
						byqs.remove(comp);
						xls.remove(comp);
						pds.remove(comp);
						return true;
					}
				}
				
				if (comp.getType() == CompanyType.BYQCY){
					if (byqs.isEmpty()){
						return true;
					}
				}
				
				if (comp.getType() == CompanyType.XLCY){
					if (xls.isEmpty()){
						return true;
					}
				}
				if (comp.getType() == CompanyType.PDCY){
					if (pds.isEmpty()){
						return true;
					}
				}
				
				return false;
			}

			@Override
			public boolean keepGroup(Company comp) {
				return true;
			}
		});
		return compSel;
	}
	
	@RequestMapping(value = "show.do")
	public ModelAndView getShow(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);

		List<Company> comps = null;
		
		map.put("pageType", PageType.SHOW.ordinal());
		String param = request.getParameter("param");
		if (null != param){
			JSONObject jo = JSONObject.fromObject(param);
			Iterator it = jo.keys();
			while (it.hasNext()){
				String key = (String) it.next();
				map.put(key, jo.get(key));
			}
			comps = extendAuthService.getAuthedCompanies(
					SessionManager.getAccount(request.getSession()),
					AuthType.QualityEntry);
		}else{
			comps = extendAuthService.getAuthedCompanies(
					SessionManager.getAccount(request.getSession()),
					AuthType.QualityLookup);
		}
		
		CompanySelection compSel = selectCompany(comps);
		compSel.select(map);
		
		return new ModelAndView("cpzlqk/cpzlqk", map);
	}
	
	@RequestMapping(value = "approve.do")
	public ModelAndView getApprove(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		
		//一级审核权限
		Set<Company> comps = new HashSet<Company>();
		List<Company> comps1 = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),53);
		List<Company> comps2 = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),54);
		List<Company> comps3 = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),AuthType.QualityApprove);
		comps.addAll(comps1);
		comps.addAll(comps2);
		comps.addAll(comps3);
		List<Company> compTmp = new ArrayList<Company>();
		for (Company comp : comps){
			compTmp.add(comp);
		}
		CompanySelection compSel = selectCompany(compTmp);
		compSel.select(map);
		
		map.put("pageType", PageType.APPROVE.ordinal());

		return new ModelAndView("cpzlqk/cpzlqk", map);
	}
	
	@RequestMapping(value = "entry.do")
	public ModelAndView getEntry(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.QualityEntry);
		CompanySelection compSel = selectCompany(comps);
		compSel.select(map);

		return new ModelAndView("cpzlqk/cpzlqkEntry", map);
	}
	
	@RequestMapping(value = "auth.do")
	public @ResponseBody byte[] getAytg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		CompanyType comp = CompanySelection.getCompany(request);
		Organization org = companyManager.getBMDBOrganization();
		Company cp = org.getCompany(comp);
		List<Company> comps1 = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),53);
		List<Company> comps2 = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),54);
		List<Company> comps3 = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),AuthType.QualityApprove);
		List<Integer> auths = new ArrayList<Integer>();
		if (comps1.contains(cp)){
			auths.add(53);
		}
		if (comps2.contains(cp)){
			auths.add(54);
		}
		if (comps3.contains(cp)){
			auths.add(AuthType.QualityApprove.ordinal());
		}
		
		return JSONArray.fromObject(auths).toString().getBytes("utf-8");
	}
}