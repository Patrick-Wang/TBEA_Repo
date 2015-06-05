package com.tbea.ic.operation.controller.webservice.entry;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.controller.webservice.account.ServiceSession;
import com.tbea.ic.operation.controller.webservice.account.ServiceUser;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.entry.EntryService;

@Controller
@WebService(endpointInterface = "com.tbea.ic.operation.controller.webservice.entry.IndicatorEntry",
serviceName = "IndicatorEntry")
public class IndicatorEntryImpl implements IndicatorEntry{

	@Autowired
	private EntryService entryService;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	private JSONArray toJson(List<Indicator> indicators){
		JSONArray jarr = new JSONArray();
		for (Indicator indi : indicators){
			JSONArray jarrItem = new JSONArray();
			jarrItem.add(indi.getId().toString());
			jarrItem.add(indi.getValue().toString());
			jarr.add(jarrItem);
		}
		return jarr;
	}

	@Override
	public void save(ServiceUser usr, Integer compId, ZBType type, Date date,
			List<Indicator> indicators) {
		Company company = companyManager.getBMDBOrganization().getCompany(compId);
		entryService.saveZb(
				new java.sql.Date(date.getTime()), 
				(Account)ServiceSession.getSession(usr).getAttribute("account"), 
				company.getType(), 
				type, 
				toJson(indicators));
	}

	@Override
	public void submit(ServiceUser usr, Integer compId, ZBType type, Date date,
			List<Indicator> indicators) {
		Company company = companyManager.getBMDBOrganization().getCompany(compId);
		entryService.submitZb(
				new java.sql.Date(date.getTime()), 
				(Account)ServiceSession.getSession(usr).getAttribute("account"), 
				company.getType(), 
				type, 
				toJson(indicators));	
	}

	@Override
	public void submit2Deputy(ServiceUser usr, Integer compId, ZBType type,
			Date date, List<Indicator> indicators) {
		Company company = companyManager.getBMDBOrganization().getCompany(compId);
		entryService.submitToDeputy(
				new java.sql.Date(date.getTime()), 
				(Account)ServiceSession.getSession(usr).getAttribute("account"), 
				company.getType(), 
				type, 
				toJson(indicators));	
		}

}
