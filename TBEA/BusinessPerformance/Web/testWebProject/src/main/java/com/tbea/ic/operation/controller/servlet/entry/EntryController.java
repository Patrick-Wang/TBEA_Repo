package com.tbea.ic.operation.controller.servlet.entry;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.CompanySelection;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExchangeRate;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.EntryService;
import com.tbea.ic.operation.service.entry.SjzbImportService;


@Controller
@RequestMapping(value = "entry")
public class EntryController {
	
	@Autowired
	private EntryService entryService;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@Autowired
	SjzbImportService sjzbImportService;
	
	@Autowired
	ApproveService approveService;
	
	private List<Company> getOwnedCompanies(Account account, ZBType entryType){
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
		return comps;
	}

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
		
		Account account = SessionManager.getAccount(request.getSession(false));
		List<Company> comps = getOwnedCompanies(account, entryType);

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
		Account account = SessionManager.getAccount(request.getSession(false));

		List<String[]> ret =  entryService.getZb(date, account, comp, entryType);
		String zb = JSONArray.fromObject(ret).toString().replace("null", "\"\"");
		List<ZBStatus> approved = entryService.getZbStatus(date, comp, entryType);
		List<ZBXX> zbxx = entryService.getZbNodes();
		Organization org = companyManager.getBMDBOrganization();
		Company company = org.getCompany(comp);
		Company zhgs = org.getCompany(CompanyType.ZHGS);
		List<ZBXX> zbxx2 = new ArrayList<ZBXX>();
		for(ZBXX z : zbxx){
			zbxx2.add(z.clone());
		}
		ExchangeRate rate = entryService.getExchangeRate(date);
		
		
		//295 QZ_GJQY295(295), // 其中_国际签约
		//录入显示时候，界面会自动将 295 指标乘以汇率累加到其父指标中，295指标本身不会乘以汇率
		String result = "{\"status\":" + JSONArray.fromObject(approved).toString() +
						", \"values\":" + zb +
						", \"zbxx\":" + JSONArray.fromObject(zbxx2).toString() +
						", \"exchangeRate\":" + (rate == null ? "1" : rate.getRate()) +
						", \"exRateZbs\" : " + "[295]"+
						", \"isJydw\":" + ((2 == company.level() && !zhgs.contains(company)) || comp == CompanyType.ZHGS) + "}"; 
		
		return result.getBytes("utf-8");
	}
	
	@RequestMapping(value = "zb_submit.do", method = RequestMethod.POST)
	public @ResponseBody byte[] getZBEntrySubmit(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("entryType")));
		Date date = DateSelection.getDate(request);
		CompanyType comp = CompanySelection.getCompany(request);
		String data = request.getParameter("data");
		Account account = SessionManager.getAccount(request.getSession(false));
		String ret = "" + entryService.submitZb(date, account, comp, entryType, JSONArray.fromObject(data), Calendar.getInstance());
		String result = "{\"result\":\"" + ret + "\"}";
		return result.getBytes("utf-8");
	}
	
	@RequestMapping(value = "zb_save.do", method = RequestMethod.POST)
	public @ResponseBody byte[] getZBEntrySave(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("entryType")));
		Date date = DateSelection.getDate(request);
		CompanyType comp = CompanySelection.getCompany(request);
		String data = request.getParameter("data");
		Account account = SessionManager.getAccount(request.getSession(false));
		String ret = "" + entryService.saveZb(date, account, comp, entryType, JSONArray.fromObject(data), Calendar.getInstance());
		String result = "{\"result\":\"" + ret + "\"}";
		return result.getBytes("utf-8");
	}
	
	@RequestMapping(value = "zb_submitToDeputy.do", method = RequestMethod.POST)
	public @ResponseBody byte[] getZBApprovementFromDeputy(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("entryType")));
		Date date = DateSelection.getDate(request);
		CompanyType comp = CompanySelection.getCompany(request);
		String data = request.getParameter("data");
		Account account = SessionManager.getAccount(request.getSession(false));
		String ret = "" + entryService.submitToDeputy(date, account, comp, entryType, JSONArray.fromObject(data), Calendar.getInstance());
		String result = "{\"result\":\"" + ret + "\"}";
		return result.getBytes("utf-8");
	}
	
	private void transport(Date d){
		Map<Company, JSONArray> data = sjzbImportService.getHBSjzb(d);
		

		LoggerFactory.getLogger("WEBSERVICE").info("transport HB sjzb");
		for (Entry<Company, JSONArray> entry : data.entrySet()){
			LoggerFactory.getLogger("WEBSERVICE").info(entry.getKey().getName());
			LoggerFactory.getLogger("WEBSERVICE").info(entry.getValue().toString());
			importData(entry, d);
		}		
		
		LoggerFactory.getLogger("WEBSERVICE").info("transport DL sjzb");
		data = sjzbImportService.getDLSjzb(d);
		for (Entry<Company, JSONArray> entry : data.entrySet()){
			LoggerFactory.getLogger("WEBSERVICE").info(entry.getKey().getName());
			LoggerFactory.getLogger("WEBSERVICE").info(entry.getValue().toString());
			importData(entry, d);
		}
		
		LoggerFactory.getLogger("WEBSERVICE").info("transport XL sjzb");
		data = sjzbImportService.getXLSjzb(d);
		for (Entry<Company, JSONArray> entry : data.entrySet()){
			LoggerFactory.getLogger("WEBSERVICE").info(entry.getKey().getName());
			LoggerFactory.getLogger("WEBSERVICE").info(entry.getValue().toString());
			importData(entry, d);
		}
	}
	
	@RequestMapping(value = "schedule.do")
	public @ResponseBody byte[] schedule(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);
		if (request.getParameter("date") != null){
			d = Date.valueOf(request.getParameter("date"));
		}
		
		
		transport(d);
		
		String result = "{\"result\":\"OK\"}";
		return result.getBytes("utf-8");
	}
	
	//每月3到五号零点触发
	@Scheduled(cron="0 0 12 4 * ?")
	public void scheduleImport(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date d = Util.toDate(cal);
		
		transport(d);
		
	}

	private void importData(ZBStatus zbStatus, JSONArray jsonArray, Date date,
			Company comp) {
		Calendar time = null;
		switch (zbStatus) {
		case APPROVED:
			List<Company> compsTmp = new ArrayList<Company>();
			compsTmp.add(comp);
			approveService.unapproveSjZb(Account.KNOWN_ACCOUNT_GFGS, compsTmp,
					date);
			entryService.submitZb(date, null, comp.getType(), ZBType.BYSJ,
					jsonArray, time);
			approveService.approveSjZb(Account.KNOWN_ACCOUNT_GFGS, compsTmp,
					date, true);
			break;
		case APPROVED_2:
			entryService.saveZb(date, null, comp.getType(), ZBType.BYSJ,
					jsonArray, time);
			List<Company> compsTmp2 = new ArrayList<Company>();
			compsTmp2.add(comp);
			approveService.approveSjZb(Account.KNOWN_ACCOUNT_JYFZ, compsTmp2,
					date, true);
			break;
		case NONE:
		case SAVED:
			entryService.saveZb(date, null, comp.getType(), ZBType.BYSJ,
					jsonArray, time);
			break;
		case SUBMITTED:
			entryService.submitZb(date, null, comp.getType(), ZBType.BYSJ,
					jsonArray, time);
			break;
		case SUBMITTED_2:
			entryService.submitToDeputy(date, null, comp.getType(),
					ZBType.BYSJ, jsonArray, time);
			break;
		default:
			break;
		}
	}
		
	private void importData(Entry<Company, JSONArray> entry, Date d) {
		ZBStatus zbStatus = entryService.getZbStatus(d, entry.getKey().getType(),
				ZBType.BYSJ).get(0);
		importData(zbStatus, entry.getValue(), d, entry.getKey());		
	}
	
}
