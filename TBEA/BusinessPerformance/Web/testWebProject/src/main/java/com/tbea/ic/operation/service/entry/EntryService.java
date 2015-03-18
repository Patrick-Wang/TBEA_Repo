package com.tbea.ic.operation.service.entry;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.entity.jygk.Account;

public interface EntryService {

	List<Company> getValidJHCompanys(Account account);

	List<Company> getValidSJCompanys(Account account);

	List<String[]> getZb(Date date, Account account, CompanyType comp,
			ZBType entryType);

	boolean updateZb(Date date, Account account, CompanyType comp,
			ZBType entryType, JSONArray data);

	public boolean hasEntryPlanPermission(Account account);

	public boolean hasEntryPredictPermission(Account account);

	boolean isApproved(Date date, CompanyType comp, ZBType entryType);
}
