package com.tbea.ic.operation.controller.servlet.approve;

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
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.service.approve.ApproveService;


@Controller
@RequestMapping(value = "approve")
public class ApproveController {
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	private ApproveService service;


	@RequestMapping(value = "zb.do", method = RequestMethod.GET)
	public ModelAndView getZBEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		ZBType approveType = ZBType.valueOf(Integer.valueOf(request.getParameter("approveType")));
		Map<String, Object> map = new HashMap<String, Object>();
		if (approveType == ZBType.NDJH){
			DateSelection dateSel = new DateSelection(year);
			dateSel.select(map);
		}
		else{			
			DateSelection dateSel = new DateSelection(year, month);
			dateSel.select(map);
		}
		Organization org = companyManager.getBMOrganization();
		
		CompanySelection compSel = new CompanySelection(false, org.getTopCompany());
		compSel.select(map);
		map.put("approveType", approveType.ordinal());
			
		return new ModelAndView("approve_template", map);
	}

	@RequestMapping(value = "zb_update.do", method = RequestMethod.POST)
	public @ResponseBody byte[] getZBApproveUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("approveType")));
		Date date = DateSelection.getDate(request);
		Organization org = companyManager.getBMOrganization();
		List<CompanyType> types = CompanySelection.getCompanys(request);
		List<Company> comps = new ArrayList<Company>();
		for (CompanyType type : types){
			comps.add(org.getCompany(type));			
		}
		
		List<List<String[]>> ret = service.getZb(comps, date, entryType);
	
		String zb = JSONArray.fromObject(ret).toString().replace("null", "");
		return zb.getBytes("utf-8");
	}
	
	@RequestMapping(value = "zb_approve.do", method = RequestMethod.POST)
	public @ResponseBody byte[] approveZB(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("approveType")));
		Date date = DateSelection.getDate(request);

		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		
		Organization org = companyManager.getBMOrganization();
		List<CompanyType> types = CompanySelection.getCompanys(data.getJSONArray(0));
		List<Company> comps = new ArrayList<Company>();
		for (CompanyType type : types){
			comps.add(org.getCompany(type));			
		}
		
		boolean ret = false;
		switch (entryType){
		case BY20YJ:
			//[[compId...]]
			ret = service.approveYj20Zb(comps, date);
			break;
		case BY28YJ:
			//[[compId...]]
			ret = service.approveYj28Zb(comps, date);
			break;
		case BYSJ:
			//[[compId...]]
			ret = service.approveSjZb(comps, date);
			break;
		case NDJH:
			//[[compId...]]
			ret = service.approveNdjhZb(comps, date);
			break;
		case YDJDMJH:
			//[[compId...], [year...], [month...]]
			ret = service.approveYdjdZb(comps, DateSelection.getDate(data.getJSONArray(1), data.getJSONArray(2)));
			break;
		default:
			break;
		}
		
		String result = "{\"result\":" + ret + "}";
		return result.getBytes("utf-8");
	}
	
	@RequestMapping(value = "zb_unapprove.do", method = RequestMethod.POST)
	public @ResponseBody byte[] unapproveZB(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("approveType")));
		Date date = DateSelection.getDate(request);
		
		JSONArray data = JSONArray.fromObject(request.getParameter("data"));
		
		Organization org = companyManager.getBMOrganization();
		List<CompanyType> types = CompanySelection.getCompanys(data.getJSONArray(0));
		List<Company> comps = new ArrayList<Company>();
		for (CompanyType type : types){
			comps.add(org.getCompany(type));			
		}
		
		boolean ret = false;
		switch (entryType){
		case BY20YJ:
			 //[[compId...], [year...], [month...]]
			ret = service.unapproveYj20Zb(comps, DateSelection.getDate(data.getJSONArray(1), data.getJSONArray(2)));
			break;
		case BY28YJ:
			 //[[compId...], [year...], [month...]]
			ret = service.unapproveYj28Zb(comps, DateSelection.getDate(data.getJSONArray(1), data.getJSONArray(2)));
			break;
		case BYSJ:
			//[[compId...]]
			ret = service.unapproveSjZb(comps, date);
			break;
		case NDJH:
			//[[compId ...]]
			ret = service.unapproveNdjhZb(comps, date);
			break;
		case YDJDMJH:
			break;
		default:
			break;
		}

		String result = "{\"result\":" + ret + "}";
		return result.getBytes("utf-8");
	}
}
