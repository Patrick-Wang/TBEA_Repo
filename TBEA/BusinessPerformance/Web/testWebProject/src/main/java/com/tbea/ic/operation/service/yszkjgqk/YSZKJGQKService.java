package com.tbea.ic.operation.service.yszkjgqk;

import java.sql.Date;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;

public interface YSZKJGQKService {

	public String[][] getYszkjg(Date d, CompanyType compType);

	public String[][] getWdqtbbh(Date d, CompanyType compType);
	
	public String[][] getJetbbh(Date d, CompanyType compType);

	public boolean IsCompanyExist(Company comp);

	public Date getLatestDate();

}
