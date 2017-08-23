package com.tbea.ic.operation.controller.servlet.cpzlqk;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
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
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.cpzlqk.CpzlqkService;
import com.tbea.ic.operation.service.cpzlqk.byqacptjjg.ByqacptjjgService;
import com.tbea.ic.operation.service.cpzlqk.byqacptjjg.ByqacptjjgServiceImpl;
import com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgwtmx.ByqcpycssbhgwtmxService;
import com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgwtmx.ByqcpycssbhgwtmxServiceImpl;
import com.tbea.ic.operation.service.cpzlqk.byqnwbzlztqk.ByqnwbzlztqkService;
import com.tbea.ic.operation.service.cpzlqk.byqnwbzlztqk.ByqnwbzlztqkServiceImpl;
import com.tbea.ic.operation.service.cpzlqk.xlacptjjg.XlacptjjgService;
import com.tbea.ic.operation.service.cpzlqk.xlacptjjg.XlacptjjgServiceImpl;
import com.tbea.ic.operation.service.cpzlqk.xlbhgcpmx.XlbhgcpmxService;
import com.tbea.ic.operation.service.cpzlqk.xlbhgcpmx.XlbhgcpmxServiceImpl;
import com.tbea.ic.operation.service.cpzlqk.xlnwbzlztqk.XlnwbzlztqkService;
import com.tbea.ic.operation.service.cpzlqk.xlnwbzlztqk.XlnwbzlztqkServiceImpl;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.tbea.ic.operation.service.report.ComponentManagerService;
import com.xml.frame.report.component.entity.Context;
import com.xml.frame.report.util.EasyList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "cpzlqk")
public class CpzlqkServlet {

	@Autowired
	ExtendAuthorityService extendAuthService;
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	CpzlqkService cpzlqkService;
	
	private CompanySelection selectCompany(List<Company> comps){
		Organization org = companyManager.getVirtualCYOrg();
		List<Company> byqs = new EasyList<Company>(org.getCompany(CompanyType.BYQCY).getSubCompanies()).clone();
		List<Company> xls = new EasyList<Company>(org.getCompany(CompanyType.XLCY).getSubCompanies()).clone();
		List<Company> pds = new EasyList<Company>(org.getCompany(CompanyType.PDCY).getSubCompanies()).clone();
		List<Company> tops = new EasyList<Company>(org.getTopCompany()).clone();
		CompanySelection compSel = new CompanySelection(false, tops, new Filter(){

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

		List<Company> comps = null;
		comps = extendAuthService.getAuthedCompanies(
				SessionManager.getAccount(request.getSession()),
				AuthType.QualityEntry);
		
		map.put("pageType", PageType.SHOW.ordinal());
		String param = request.getParameter("param");
		if (null != param){
			JSONObject jo = JSONObject.fromObject(param);
			Iterator it = jo.keys();
			while (it.hasNext()){
				String key = (String) it.next();
				map.put(key, jo.get(key));
			}
			
			CompanyType compType = CompanyType.valueOf(Integer.valueOf(jo.getString("companyId")));
			comps = new ArrayList<Company>();
			comps.add(companyManager.getBMDBOrganization().getCompany(compType));
		}else{
			if(comps.isEmpty()){
				comps = extendAuthService.getAuthedCompanies(
						SessionManager.getAccount(request.getSession()),
						AuthType.QualityLookup);
			}else{
				map.put("pageType", PageType.ENTRY.ordinal());
			}
		}
		
		CompanySelection compSel = selectCompany(comps);
		compSel.select(map);
		
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") +"cpzlqk/cpzlqk", map);
	}
	
	@RequestMapping(value = {"approve.do", "v2/approve.do"})
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

		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") +"cpzlqk/cpzlqk", map);
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
		CompanySelection compSel = selectCompany(comps);
		compSel.select(map);

		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") +"cpzlqk/cpzlqkEntry", map);
	}
	
	@RequestMapping(value = "doApprove.do")
	public @ResponseBody byte[] approve(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		if (null == company) {
			company = companyManager.getBMDBOrganization().getCompany(comp);
		}
		ZBStatus status = ZBStatus.valueOf(Integer.valueOf(request.getParameter("zt")));
		ErrorCode er = cpzlqkService.approve(d, company, status);
		return Util.response(er);
	}
	
	@RequestMapping(value = "auth.do")
	public @ResponseBody byte[] getAytg(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		if (null == company) {
			company = companyManager.getBMDBOrganization().getCompany(comp);
		}
		Account account = SessionManager.getAccount(request.getSession());
		List<Integer> auths = extendAuthService.getAuths(account, company);
		return JSONArray.fromObject(auths).toString().getBytes("utf-8");
	}
	
	@Autowired
	ComponentManagerService cms;
	
	@Resource(name=ByqacptjjgServiceImpl.NAME)
	ByqacptjjgService byqacptjjgService;

	@Resource(name=XlacptjjgServiceImpl.NAME)
	XlacptjjgService xlacptjjgService;
	

	@Resource(name=ByqcpycssbhgwtmxServiceImpl.NAME)
	ByqcpycssbhgwtmxService byqcpycssbhgwtmxService;
	

	@Resource(name=XlbhgcpmxServiceImpl.NAME)
	XlbhgcpmxService xlbhgcpmxService;
	
	@Resource(name=ByqnwbzlztqkServiceImpl.NAME)
	ByqnwbzlztqkService byqnwbzlztqkService;
	
	@Resource(name=XlnwbzlztqkServiceImpl.NAME)
	XlnwbzlztqkService xlnwbzlztqkService;
	
	void removeColumns(List<List<String>> result, int start, int count){
		for (int i = 0; i < result.size(); ++i) {
			for (int j = 0; j< count; ++j) {
				result.get(i).remove(start);
			}
		}
	}
	
	List<List<String>> replaceNull(List<List<String>> result){
		for (int i = 0; i < result.size(); ++i) {
			for (int j = 0; j< result.get(i).size(); ++j) {
				if (null == result.get(i).get(j)) {
					result.get(i).set(j, "--");
				}
				
			}
		}
		return result;
	}
	
	@RequestMapping(value = "cpzlfxbg.do")
	public void cpzlfxbg(HttpServletRequest request,
			HttpServletResponse response)  {
		Date d = Date.valueOf(request.getParameter("date"));
		CompanyType comp = CompanySelection.getCompany(request);
		Company company = companyManager.getVirtualCYOrg().getCompany(comp);
		Context context = new Context();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<Integer> zts = new ArrayList<Integer>();
		zts.add(ZBStatus.APPROVED.ordinal());
		context.put("dwmc", company.getName());
		context.put("year", cal.get(Calendar.YEAR));
		context.put("month", cal.get(Calendar.MONTH) + 1);
		if (company.getType() == CompanyType.BYQCY ||
			companyManager.getVirtualCYOrg().getCompany(CompanyType.BYQCY).getSubCompanies().contains(company)){
			

			List<List<String>> result = byqacptjjgService.getByqacptjjg(d, company, YDJDType.YD, zts);
			removeColumns(result, 0, 2);
			context.put("cpycss", result);
			
			if (comp == CompanyType.BYQCY){
				result = byqcpycssbhgwtmxService.getByqcpycssbhgwtmx(d, YDJDType.YD, zts);
			}else{
				result = byqcpycssbhgwtmxService.getByqcpycssbhgwtmx(d, YDJDType.YD, company, zts);
			}
			
			removeColumns(result, 10, 1);
			removeColumns(result, 6, 1);
			removeColumns(result, 1, 2);
			context.put("ycssbhgcpxx", result);
			
			List<List<String>> nwbzlwtqk = new ArrayList<List<String>>();
			if (comp == CompanyType.BYQCY){
				result = byqnwbzlztqkService.getYdnwbzlqk(d, zts);
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				nwbzlwtqk.add(result.get(result.size() - 1).subList(1, 4));
				nwbzlwtqk.add(result.get(result.size() - 1).subList(4, 7));
				calTmp.add(Calendar.YEAR, -1);
				result = byqnwbzlztqkService.getYdnwbzlqk(new Date(calTmp.getTimeInMillis()), zts);
				nwbzlwtqk.add(result.get(result.size() - 1).subList(1, 4));
				nwbzlwtqk.add(result.get(result.size() - 1).subList(4, 7));
			}else{
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				result = byqnwbzlztqkService.getYdnwbzlqk(d, company, zts);
				nwbzlwtqk.add(result.get(0).subList(1, 4));
				nwbzlwtqk.add(result.get(1).subList(1, 4));
				calTmp.add(Calendar.YEAR, -1);
				result = byqnwbzlztqkService.getYdnwbzlqk(new Date(calTmp.getTimeInMillis()), company, zts);
				nwbzlwtqk.add(result.get(0).subList(1, 4));
				nwbzlwtqk.add(result.get(1).subList(1, 4));
			}
			
			
			context.put("nwbzlwtqk", replaceNull(nwbzlwtqk));
			
			
			List<List<String>> sjzlqk = new ArrayList<List<String>>();
			if (comp == CompanyType.BYQCY){
				result = byqnwbzlztqkService.getYdsjzlqk(d, zts);
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				sjzlqk.add(result.get(result.size() - 1).subList(1, 4));
				sjzlqk.add(result.get(result.size() - 1).subList(4, 7));
				calTmp.add(Calendar.YEAR, -1);
				result = byqnwbzlztqkService.getYdsjzlqk(new Date(calTmp.getTimeInMillis()), zts);
				sjzlqk.add(result.get(result.size() - 1).subList(1, 4));
				sjzlqk.add(result.get(result.size() - 1).subList(4, 7));
			}else{
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				result = byqnwbzlztqkService.getYdsjzlqk(d, company, zts);
				sjzlqk.add(result.get(0).subList(1, 4));
				sjzlqk.add(result.get(1).subList(1, 4));
				calTmp.add(Calendar.YEAR, -1);
				result = byqnwbzlztqkService.getYdsjzlqk(new Date(calTmp.getTimeInMillis()), company, zts);
				sjzlqk.add(result.get(0).subList(1, 4));
				sjzlqk.add(result.get(1).subList(1, 4));
			}
			
			context.put("sjzlqk", replaceNull(sjzlqk));
			
			List<List<String>> yclzpjzlqk = new ArrayList<List<String>>();
			if (comp == CompanyType.BYQCY){
				result = byqnwbzlztqkService.getYdYclzlwt(d, zts);
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				yclzpjzlqk.add(result.get(result.size() - 1).subList(1, 5));
				yclzpjzlqk.add(result.get(result.size() - 1).subList(5, 9));
				calTmp.add(Calendar.YEAR, -1);
				result = byqnwbzlztqkService.getYdYclzlwt(new Date(calTmp.getTimeInMillis()), zts);
				yclzpjzlqk.add(result.get(result.size() - 1).subList(1, 5));
				yclzpjzlqk.add(result.get(result.size() - 1).subList(5, 9));
			}else{
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				result = byqnwbzlztqkService.getYdYclzlwt(d, company, zts);
				yclzpjzlqk.add(result.get(0).subList(1, 5));
				yclzpjzlqk.add(result.get(1).subList(1, 5));
				calTmp.add(Calendar.YEAR, -1);
				result = byqnwbzlztqkService.getYdYclzlwt(new Date(calTmp.getTimeInMillis()), company, zts);
				yclzpjzlqk.add(result.get(0).subList(1, 5));
				yclzpjzlqk.add(result.get(1).subList(1, 5));
			}
			context.put("yclzpjzlqk", replaceNull(yclzpjzlqk));
			
			List<List<String>> gczzzlqk = new ArrayList<List<String>>();
			if (comp == CompanyType.BYQCY){
				result = byqnwbzlztqkService.getYdSczzzlqk(d, zts);
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				gczzzlqk.add(result.get(result.size() - 1).subList(1, 4));
				gczzzlqk.add(result.get(result.size() - 1).subList(4, 7));
				calTmp.add(Calendar.YEAR, -1);
				result = byqnwbzlztqkService.getYdSczzzlqk(new Date(calTmp.getTimeInMillis()), zts);
				gczzzlqk.add(result.get(result.size() - 1).subList(1, 4));
				gczzzlqk.add(result.get(result.size() - 1).subList(4, 7));
			}else{
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				result = byqnwbzlztqkService.getYdSczzzlqk(d, company, zts);
				gczzzlqk.add(result.get(0).subList(1, 4));
				gczzzlqk.add(result.get(1).subList(1, 4));
				calTmp.add(Calendar.YEAR, -1);
				result = byqnwbzlztqkService.getYdSczzzlqk(new Date(calTmp.getTimeInMillis()), company, zts);
				gczzzlqk.add(result.get(0).subList(1, 4));
				gczzzlqk.add(result.get(1).subList(1, 4));
			}			
			context.put("gczzzlqk", replaceNull(gczzzlqk));
			
			List<List<String>> gczzwtdyxmgscjfbqk = null;
			if (comp == CompanyType.BYQCY){
				List<List<String>> resultNow = byqnwbzlztqkService.getYdSczzzlqkxxxx(d, zts);
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				calTmp.add(Calendar.YEAR, -1);
				List<List<String>> resultLast = byqnwbzlztqkService.getYdSczzzlqkxxxx(new Date(calTmp.getTimeInMillis()), zts);
				gczzwtdyxmgscjfbqk = merge(resultNow, resultLast);
			}else{
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				List<List<String>> resultNow = byqnwbzlztqkService.getYdSczzzlqkxxxx(d, company, zts);
				calTmp.add(Calendar.YEAR, -1);
				List<List<String>> resultLast = byqnwbzlztqkService.getYdSczzzlqkxxxx(new Date(calTmp.getTimeInMillis()), company, zts);
				gczzwtdyxmgscjfbqk = merge(resultNow, resultLast);
			}
			context.put("gczzwtdyxmgscjfbqk", replaceNull(gczzwtdyxmgscjfbqk));
			try {
				cms.doController(request, response, "zlfxReportByqClr", context);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			
			List<List<String>> result = xlacptjjgService.getXlacptjjg(d, company, YDJDType.YD, zts);
			removeColumns(result, 0, 2);
			context.put("cpycss", result);
			
			if (comp == CompanyType.XLCY){
				result = xlbhgcpmxService.getXlbhgcpmx(d, zts);
			}else{
				result = xlbhgcpmxService.getXlbhgcpmx(d, company, zts);
			}
			removeColumns(result, 10, 1);
			removeColumns(result, 6, 1);
			removeColumns(result, 1, 2);
			context.put("ycssbhgcpxx", result);
			
			List<List<String>> nwbzlwtqk = new ArrayList<List<String>>();
			if (comp == CompanyType.XLCY){
				result = xlnwbzlztqkService.getYdnwbzlqk(d, zts);
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				nwbzlwtqk.add(result.get(result.size() - 1).subList(1, 4));
				nwbzlwtqk.add(result.get(result.size() - 1).subList(4, 7));
				calTmp.add(Calendar.YEAR, -1);
				result = xlnwbzlztqkService.getYdnwbzlqk(new Date(calTmp.getTimeInMillis()), zts);
				nwbzlwtqk.add(result.get(result.size() - 1).subList(1, 4));
				nwbzlwtqk.add(result.get(result.size() - 1).subList(4, 7));
			}else{
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				result = xlnwbzlztqkService.getYdnwbzlqk(d, company, zts);
				nwbzlwtqk.add(result.get(0).subList(1, 4));
				nwbzlwtqk.add(result.get(1).subList(1, 4));
				calTmp.add(Calendar.YEAR, -1);
				result = xlnwbzlztqkService.getYdnwbzlqk(new Date(calTmp.getTimeInMillis()), company, zts);
				nwbzlwtqk.add(result.get(0).subList(1, 4));
				nwbzlwtqk.add(result.get(1).subList(1, 4));
			}
			context.put("nwbzlwtqk", replaceNull(nwbzlwtqk));
			
			List<List<String>> gyzlqk = new ArrayList<List<String>>();
			if (comp == CompanyType.XLCY){
				result = xlnwbzlztqkService.getYdGyzlwt(d, zts);
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				gyzlqk.add(result.get(result.size() - 1).subList(1, 4));
				gyzlqk.add(result.get(result.size() - 1).subList(4, 7));
				calTmp.add(Calendar.YEAR, -1);
				result = xlnwbzlztqkService.getYdGyzlwt(new Date(calTmp.getTimeInMillis()), zts);
				gyzlqk.add(result.get(result.size() - 1).subList(1, 4));
				gyzlqk.add(result.get(result.size() - 1).subList(4, 7));
			}else{
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				result = xlnwbzlztqkService.getYdGyzlwt(d, company, zts);
				gyzlqk.add(result.get(0).subList(1, 4));
				gyzlqk.add(result.get(1).subList(1, 4));
				calTmp.add(Calendar.YEAR, -1);
				result = xlnwbzlztqkService.getYdGyzlwt(new Date(calTmp.getTimeInMillis()), company, zts);
				gyzlqk.add(result.get(0).subList(1, 4));
				gyzlqk.add(result.get(1).subList(1, 4));
			}
			context.put("gyzlqk", replaceNull(gyzlqk));
			
			List<List<String>> yclzpjzlqk = new ArrayList<List<String>>();
			if (comp == CompanyType.XLCY){
				result = xlnwbzlztqkService.getYdYclzlwt(d, zts);
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				yclzpjzlqk.add(result.get(result.size() - 1).subList(1, 5));
				yclzpjzlqk.add(result.get(result.size() - 1).subList(5, 9));
				calTmp.add(Calendar.YEAR, -1);
				result = xlnwbzlztqkService.getYdYclzlwt(new Date(calTmp.getTimeInMillis()), zts);
				yclzpjzlqk.add(result.get(result.size() - 1).subList(1, 5));
				yclzpjzlqk.add(result.get(result.size() - 1).subList(5, 9));
			}else{
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				result = xlnwbzlztqkService.getYdYclzlwt(d, company, zts);
				yclzpjzlqk.add(result.get(0).subList(1, 5));
				yclzpjzlqk.add(result.get(1).subList(1, 5));
				calTmp.add(Calendar.YEAR, -1);
				result = xlnwbzlztqkService.getYdYclzlwt(new Date(calTmp.getTimeInMillis()), company, zts);
				yclzpjzlqk.add(result.get(0).subList(1, 5));
				yclzpjzlqk.add(result.get(1).subList(1, 5));
			}
			context.put("yclzpjzlqk", replaceNull(yclzpjzlqk));
			
			List<List<String>> gczzzlqk = new ArrayList<List<String>>();
			if (comp == CompanyType.XLCY){
				result = xlnwbzlztqkService.getYdSczzzlqk(d, zts);
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				gczzzlqk.add(result.get(result.size() - 1).subList(1, 4));
				gczzzlqk.add(result.get(result.size() - 1).subList(4, 7));
				calTmp.add(Calendar.YEAR, -1);
				result = xlnwbzlztqkService.getYdSczzzlqk(new Date(calTmp.getTimeInMillis()), zts);
				gczzzlqk.add(result.get(result.size() - 1).subList(1, 4));
				gczzzlqk.add(result.get(result.size() - 1).subList(4, 7));
			}else{
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				result = xlnwbzlztqkService.getYdSczzzlqk(d, company, zts);
				gczzzlqk.add(result.get(0).subList(1, 4));
				gczzzlqk.add(result.get(1).subList(1, 4));
				calTmp.add(Calendar.YEAR, -1);
				result = xlnwbzlztqkService.getYdSczzzlqk(new Date(calTmp.getTimeInMillis()), company, zts);
				gczzzlqk.add(result.get(0).subList(1, 4));
				gczzzlqk.add(result.get(1).subList(1, 4));
			}		
			context.put("gczzzlqk", replaceNull(gczzzlqk));
			
			
			List<List<String>> gczzwtdyxmgscjfbqk = null;
			if (comp == CompanyType.XLCY){
				List<List<String>> resultNow = xlnwbzlztqkService.getYdSczzzlqkxxxx(d, zts);
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				calTmp.add(Calendar.YEAR, -1);
				List<List<String>> resultLast = xlnwbzlztqkService.getYdSczzzlqkxxxx(new Date(calTmp.getTimeInMillis()), zts);
				gczzwtdyxmgscjfbqk = merge(resultNow, resultLast);
			}else{
				Calendar calTmp = Calendar.getInstance();
				calTmp.setTime(d);
				List<List<String>> resultNow = xlnwbzlztqkService.getYdSczzzlqkxxxx(d, company, zts);
				calTmp.add(Calendar.YEAR, -1);
				List<List<String>> resultLast = xlnwbzlztqkService.getYdSczzzlqkxxxx(new Date(calTmp.getTimeInMillis()), company, zts);
				gczzwtdyxmgscjfbqk = merge(resultNow, resultLast);
			}
			context.put("gczzwtdyxmgscjfbqk", replaceNull(gczzwtdyxmgscjfbqk));
			
			try {
				cms.doController(request, response, "zlfxReportXlClr", context);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	private List<List<String>> merge(List<List<String>> resultNow, List<List<String>> resultLast) {
		List<List<String>> gczzzlqk = new ArrayList<List<String>>();
		Map<String, Integer> mergeMap = new HashMap<String, Integer>();
		for (int i = 0; i < resultNow.size(); ++i) {
			mergeMap.put(resultNow.get(i).get(1), i);
			gczzzlqk.add(resultNow.get(i).subList(1, 4));
		}
		
		for (int i = 0; i < resultLast.size(); ++i) {
			if (!mergeMap.containsKey(resultLast.get(i).get(1))) {
				mergeMap.put(resultLast.get(i).get(1), gczzzlqk.size());
				List<String> list = new ArrayList<String>();
				list.add(resultLast.get(i).get(1));
				list.add(null);
				list.add(null);
				list.add(null);
			}
			gczzzlqk.get(mergeMap.get(resultLast.get(i).get(1))).addAll(resultLast.get(i).subList(2, 4));
		}
		
		for (int i = 0; i < gczzzlqk.size(); ++i) {
			if (gczzzlqk.get(i).size() == 3) {
				gczzzlqk.get(i).add(null);
				gczzzlqk.get(i).add(null);
				gczzzlqk.get(i).add(null);
			}
		}
		
		return gczzzlqk;
	}
}