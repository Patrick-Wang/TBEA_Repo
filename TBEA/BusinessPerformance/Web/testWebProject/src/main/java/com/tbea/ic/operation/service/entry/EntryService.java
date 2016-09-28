package com.tbea.ic.operation.service.entry;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.entity.ExchangeRate;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;
import com.tbea.ic.operation.service.entry.ZBListenerAggregator.IndiValues;

public interface EntryService {

	List<Company> getValidJHCompanys(Account account);

	List<Company> getValidSJCompanys(Account account);

	List<String[]> getZb(Date date, Account account, CompanyType comp,
			ZBType entryType);

	boolean submitZb(Date date, Account account, CompanyType comp,
			ZBType entryType, JSONArray data, Calendar time);

	public boolean hasEntryPlanPermission(Account account);

	public boolean hasEntryPredictPermission(Account account);

//	List<Boolean> isApproved(Date date, CompanyType comp, ZBType entryType);

	List<String[]> getEntryStatus(Date date, ZBType entryType, List<Company> mainCompanies);

	boolean saveZb(Date date, Account account, CompanyType comp,
			ZBType entryType, JSONArray data, Calendar time);
	
	//Add Approvement function for Deputy Manager 
	boolean submitToDeputy(Date date, Account account, CompanyType comp,
			ZBType entryType, JSONArray data, Calendar time);

	List<ZBStatus> getZbStatus(Date date, CompanyType comp, ZBType entryType);

	boolean hasMarketPermission(Account account);

	List<ZBXX> getZbNodes();

	ExchangeRate getExchangeRate(Date date);
}
