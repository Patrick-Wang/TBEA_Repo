package com.tbea.test.testWebProject.service.yszkjgqk;

import java.sql.Date;
import java.util.Set;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.common.companys.CompanyManager.CompanyType;

public interface YSZKJGQKService {

	public String[][] getYszkjg(Date d, Company comp);

	public String[][] getWdqtbbh(Date d, Company comp);
	public String[][] getJetbbh(Date d, Company comp);

	public boolean IsCompanyExist(Company comp);

}
