package com.tbea.ic.operation.controller.servlet.jygk.zzy;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.service.jygk.zzy.ZzyLrsjService;


@Controller
@RequestMapping(value = "zzy_lrsj")
public class ZzyLrsjController {
	
	@Autowired
	private ZzyLrsjService zzyLrsjService;
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	private List<Company> getOwnedCompanies(Account account){
		Organization org = companyManager.getBMDBOrganization();
		List<Company> comps = new ArrayList<Company>();	
		Set<DWXX> dwxxset=account.getDwxxs();
		Iterator<DWXX> dwxxiterator=dwxxset.iterator();
		 while(dwxxiterator.hasNext()){
			 DWXX dwxx = dwxxiterator.next();
			 Company comp=org.getCompany(dwxx.getId());
			 comps.add(comp);
		 }
		return comps;
	}
	
	//录入入口
	@RequestMapping(value = "zb.do", method = RequestMethod.GET)
	public ModelAndView getZBBG(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		//给页面返回报表ID和名称
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bg", zzyLrsjService.getLrsjBgList());
		return new ModelAndView("jygkzzy/zzy_lrsj_template", map);
	}

	@RequestMapping(value = "zb_entry.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getZBEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		Map<String, Object> map = new HashMap<String, Object>();
		if("10002".equals(request.getParameter("entryType")) || "10003".equals(request.getParameter("entryType"))){
			DateSelection dateSel = new DateSelection(year, month);
			dateSel.select(map);
		} else {
			DateSelection dateSel = new DateSelection(year, month);
			dateSel.select(map);
		}
		//设置可选公司到map
		Account account = SessionManager.getAccount(request.getSession(false));		
		List<Company> comps = getOwnedCompanies(account);
		CompanySelection compSel = new CompanySelection(true, comps);		
		compSel.select(map);		
		String result = JSONObject.fromObject(map).toString(); 
		return result.getBytes("utf-8");
	}

	@RequestMapping(value = "zb_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getZBEntryUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date date = DateSelection.getDate(request);
		String dwxxId="";
		String companyId = request.getParameter("companyId");
		if (null != companyId){
			int cid = Integer.parseInt(companyId);
			CompanyType companyType=CompanyType.valueOf(cid);
			Company company=companyManager.getBMDBOrganization().getCompany(companyType);
			dwxxId=company.getId().toString();
		}
		List<String[]> ret =  zzyLrsjService.getZb(date,dwxxId, request.getParameter("entryType"));
		String zb = JSONArray.fromObject(ret).toString().replace("null", "\"\"");
		String result = "{\"values\":" + zb + "}"; 
		
		return result.getBytes("utf-8");
	}
	
	@RequestMapping(value = "zb_save.do", method = RequestMethod.POST)
	public @ResponseBody byte[] getZBEntrySave(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
//		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("entryType")));
		Date date = DateSelection.getDate(request);
		String data = request.getParameter("data");
		Account account = SessionManager.getAccount(request.getSession(false));
		String dwxxId="";
		String companyId = request.getParameter("companyId");
		if (null != companyId){
			int cid = Integer.parseInt(companyId);
			CompanyType companyType=CompanyType.valueOf(cid);
			Company company=companyManager.getBMDBOrganization().getCompany(companyType);
			dwxxId=company.getId().toString();
		}
		String ret = "" + zzyLrsjService.saveZb(date, dwxxId, request.getParameter("entryType"), JSONArray.fromObject(data));
		String result = "{\"result\":\"" + ret + "\"}";
		return result.getBytes("utf-8");
	}
}
