package com.tbea.ic.operation.controller.servlet.nwbzlqk;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tbea.ic.operation.service.report.HBWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.CompanySelection.Filter;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Url;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.controller.servlet.cpzlqk.PageType;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.cpzlqk.CpzlqkService;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.xml.frame.report.util.EasyList;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "nwbzlqk")
public class NwbzlqkServlet {

	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@Autowired
	CpzlqkService cpzlqkService;
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	private CompanySelection selectCompany(List<Company> comps){
		Organization org = companyManager.getVirtualCYOrg();
		List<Company> byqs = new EasyList<Company>(org.getCompanyByType(CompanyType.BYQCY).getSubCompanies()).clone();
		List<Company> xls = new EasyList<Company>(org.getCompanyByType(CompanyType.XLCY).getSubCompanies()).clone();
		List<Company> pds = new EasyList<Company>(org.getCompanyByType(CompanyType.PDCY).getSubCompanies()).clone();

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
				if (comp.getType() == CompanyType.XKGS) {
					for (Company cp : comps){
						if (cp.getType() == CompanyType.XKGS){
							return true;
						}
					}
					return false;
				}
				return true;
			}
		});
		return compSel;
	}
	
	@RequestMapping(value = {"show.do", "v2/show.do"})
	public ModelAndView getShow(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		
		
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.QualityEntry);
		
		if(comps.isEmpty()){
			comps = extendAuthService.getAuthedCompanies(
					SessionManager.getAccount(request.getSession()),
					AuthType.QualityLookup);
			map.put("pageType", PageType.SHOW.ordinal());
		}else{
			map.put("pageType", PageType.ENTRY.ordinal());
		}
		
		CompanySelection compSel = selectCompany(comps);
		compSel.select(map);
		
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "nwbzlqk/nwbzlqk", map);
	}
	
	
	@RequestMapping(value =  {"approve.do", "v2/approve.do"})
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
				SessionManager.getAccount(request.getSession()),55);
		List<Company> comps4 = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),AuthType.QualityApprove);
		comps.addAll(comps1);
		comps.addAll(comps2);
		comps.addAll(comps3);
		comps.addAll(comps4);
		List<Company> compTmp = new ArrayList<Company>();
		for (Company comp : comps){
			compTmp.add(comp);
		}
		CompanySelection compSel = selectCompany(compTmp);
		compSel.select(map);
		
		map.put("pageType", PageType.APPROVE.ordinal());

		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "nwbzlqk/nwbzlqk", map);
	}
	
	@RequestMapping(value = {"entry.do", "v2/entry.do"})
	public ModelAndView getEntry(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();	
		DateSelection dateSel = new DateSelection(Calendar.getInstance(), true, false);
		dateSel.select(map);
		List<Company> comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.QualityEntry);
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "nwbzlqk/nwbzlqkEntry", map);
	}
	
	@RequestMapping(value = "doApprove.do")
	public @ResponseBody byte[] approve(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		ZBStatus status = ZBStatus.valueOf(Integer.valueOf(request.getParameter("zt")));
		ErrorCode er = cpzlqkService.approveNwbzlqk(d, company, status);
		return Util.response(er);
	}
	
	@RequestMapping(value = "auth.do")
	public @ResponseBody byte[] getAytg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompanyByType(comp);
		Account account = SessionManager.getAccount(request.getSession());
		List<Integer> auths = extendAuthService.getAuths(account, company);
		return JSONArray.fromObject(auths).toString().getBytes("utf-8");
	}

    @RequestMapping(value = {"testhbnbzlqk"})
    public  @ResponseBody byte[] testHb(HttpServletRequest request,
                                HttpServletResponse response) throws UnsupportedEncodingException {
        Date d = DateSelection.getDate(request);
        HBWebService hws = new HBWebService();
        List<String> cols = new ArrayList<String>();
        cols.add("company_name");
cols.add("issue_happen_date");
cols.add("product_type");
cols.add("production_num");
cols.add("production_model");
cols.add("issue_type");
cols.add("sub_issue_type");
cols.add("category_code");
cols.add("material_quality_phenomenon");
cols.add("detail");
cols.add("material_happen_phase");
cols.add("material_count");
cols.add("measurement_units");
cols.add("suppier_id");
cols.add("suppier");
cols.add("issue_process");
cols.add("responsibility_department");
cols.add("material_treatment_measure");
cols.add("onsite_treatmen_measure");
cols.add("onsite_treatment_result");
cols.add("causa_analysis");
cols.add("assessment");
cols.add("filling_personnel");
        hws.getHBNbzlqk(cols);
      return "OK".getBytes("utf-8");
    }
}