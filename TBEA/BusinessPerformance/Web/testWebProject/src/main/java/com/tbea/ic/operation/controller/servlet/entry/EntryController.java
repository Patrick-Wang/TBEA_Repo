package com.tbea.ic.operation.controller.servlet.entry;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.entity.User;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.EntryService;


@Controller
@RequestMapping(value = "entry")
public class EntryController {
	
	@Autowired
	private EntryService entryService;


	@RequestMapping(value = "zb.do", method = RequestMethod.GET)
	public ModelAndView getZBEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("entryType")));
		Map<String, Object> map = new HashMap<String, Object>();
		if (entryType == ZBType.NDJH){
			DateSelection dateSel = new DateSelection(year);
			dateSel.select(map);
		}
		else{
			DateSelection dateSel = new DateSelection(year, month);
			dateSel.select(map);
		}
		Account account = (Account) request.getSession(false).getAttribute("account");
		
		List<Company> comps = null;
		
		switch (entryType){
			case NDJH:
			case YDJDMJH:
				comps = entryService.getValidJHCompanys(account);
				break;
				
			case BY20YJ:
			case BY28YJ:
			case BYSJ:
				comps = entryService.getValidSJCompanys(account);
				break;	
		}
		
		CompanySelection compSel = new CompanySelection(true, comps);
		compSel.select(map);
		
		map.put("entryType", entryType.ordinal());		
		return new ModelAndView("entry_template", map);
	}

	
	
	@RequestMapping(value = "zb_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getZBEntryUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		CompanyType comp = CompanySelection.getCompany(request);
		Date date = DateSelection.getDate(request);
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("entryType")));
		Account account = (Account) request.getSession(false).getAttribute("account");

		List<String[]> ret =  entryService.getZb(date, account, comp, entryType);
		String zb = JSONArray.fromObject(ret).toString().replace("null", "\"\"");
		return zb.getBytes("utf-8");
	}
	
	@RequestMapping(value = "zb_submit.do", method = RequestMethod.POST)
	public @ResponseBody byte[] getZBEntrySubmit(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("entryType")));
		Date date = DateSelection.getDate(request);
		CompanyType comp = CompanySelection.getCompany(request);
		String data = request.getParameter("data");
		boolean ret = false;
		Account account = (Account) request.getSession(false).getAttribute("account");
		ret = entryService.updateZb(date, account, comp, entryType, JSONArray.fromObject(data));
		
		String result = "{\"result\":" + ret + "}";
		return result.getBytes("utf-8");
	}
}
