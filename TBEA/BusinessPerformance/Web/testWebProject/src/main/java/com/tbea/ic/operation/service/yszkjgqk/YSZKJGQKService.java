package com.tbea.ic.operation.service.yszkjgqk;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;

public interface YSZKJGQKService {

	public String[][] getYszkjg(Date d, Company comp);

	public String[][] getYszkjg(Date d, List<Company> comps);
	
	public String[][] getWdqtbbh(Date d, Company comp);
	
	public String[][] getWdqtbbh(Date d, List<Company> comps);
	
	public String[][] getJetbbh(Date d, Company comp);
	
	public String[][] getJetbbh(Date d, List<Company> comps);

	public boolean IsCompanyExist(Company comp);

	public Date getLatestDate();

}
