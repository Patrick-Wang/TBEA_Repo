package com.tbea.ic.operation.controller.servlet.approve;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.entity.Permission;
import com.tbea.ic.operation.model.entity.User;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.cb.XLCBService;
import com.tbea.ic.operation.service.entry.EntryService;


@Controller
@RequestMapping(value = "approve")
public class ApproveController {
	
	@Autowired
	private ApproveService service;
	
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public ModelAndView getIndexEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		User usr = new User();
		boolean hasPermission = service.hasApprovePlanPermission(usr);
		map.put("approvePlan", hasPermission);
		hasPermission = service.hasApprovePredictPermission(usr);
		map.put("approvePredict", hasPermission);
	
		return new ModelAndView("approve_index", map);
	}
	
	@RequestMapping(value = "zb.do", method = RequestMethod.GET)
	public ModelAndView getZBEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		ZBType approveType = ZBType.valueOf(Integer.valueOf(request.getParameter("approveType")));
		Map<String, Object> map = new HashMap<String, Object>();
		if (approveType == ZBType.QNJH){
			DateSelection dateSel = new DateSelection(year);
			dateSel.select(map);
		}
		else{
			
			DateSelection dateSel = new DateSelection(year, month);
			dateSel.select(map);
		}
		Organization org = CompanyManager.getBMOrganization();
		
		CompanySelection compSel = new CompanySelection(false, org.getTopCompany());
		compSel.setFirstCompany(CompanyType.HNGCGS);
		compSel.select(map);
		map.put("approveType", approveType.ordinal());
			
		return new ModelAndView("approve_template", map);
	}

	
	
	@RequestMapping(value = "zb_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getZBApproveUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("approveType")));
		Date date = DateSelection.getDate(request);
		User usr = new User();
		List<String[][]> ret = null;
		ret =  service.getZb(date, usr, entryType);
	
		String zb = JSONArray.fromObject(ret).toString().replace("null", "");
		return zb.getBytes("utf-8");
	}
	
	@RequestMapping(value = "zb_approve.do", method = RequestMethod.POST)
	public @ResponseBody byte[] approveZB(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("approveType")));
		Date date = DateSelection.getDate(request);
		String data = request.getParameter("data");
		boolean ret = false;
		User usr = new User();
		ret = service.approveZb(date, usr, JSONArray.fromObject(data), entryType);
		
		
//		switch (entryType){
//			case BY20:
//				ret = service.update20Zb(date, JSONArray.fromObject(data));
//				break;
//			case BY28:
//				ret = service.update28Zb(date, usr, JSONArray.fromObject(data));
//				break;
//			case BYSJ:
//				ret = service.updateBySJZb(date, JSONArray.fromObject(data));
//				break;
//			case QNJH:
//				ret = service.updateByQNZb(date, JSONArray.fromObject(data));
//				break;
//			default:
//				break;
//		}
		
		String result = "{\"result\":" + ret + "}";
		return result.getBytes("utf-8");
	}
	
	@RequestMapping(value = "zb_unapprove.do", method = RequestMethod.POST)
	public @ResponseBody byte[] unapproveZB(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("approveType")));
		Date date = DateSelection.getDate(request);
		String data = request.getParameter("data");
		boolean ret = false;
		User usr = new User();
		ret = service.unapproveZb(date, usr, JSONArray.fromObject(data), entryType);
		
		
//		switch (entryType){
//			case BY20:
//				ret = service.update20Zb(date, JSONArray.fromObject(data));
//				break;
//			case BY28:
//				ret = service.update28Zb(date, usr, JSONArray.fromObject(data));
//				break;
//			case BYSJ:
//				ret = service.updateBySJZb(date, JSONArray.fromObject(data));
//				break;
//			case QNJH:
//				ret = service.updateByQNZb(date, JSONArray.fromObject(data));
//				break;
//			default:
//				break;
//		}
		
		String result = "{\"result\":" + ret + "}";
		return result.getBytes("utf-8");
	}
}
