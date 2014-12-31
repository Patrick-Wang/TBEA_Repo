package com.tbea.test.testWebProject.model.dao.xlfkfstj;

import java.sql.Date;
import java.util.List;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.model.entity.XLFDWFKFS;
import com.tbea.test.testWebProject.model.entity.XLGWFKFS;
import com.tbea.test.testWebProject.model.entity.XLNWFKFS;

public interface XLFKFSDao {

	List<XLFDWFKFS> getFdwfkfs(Date d, Company comp);

	List<XLGWFKFS> getGwfkfs(Date d, Company comp);

	List<XLNWFKFS> getNwfkfs(Date d, Company comp);

	boolean fdwContainsCompany(Company comp);
	
	boolean gwContainsCompany(Company comp);
	
	boolean nwContainsCompany(Company comp);

	XLFDWFKFS getLatestFdwfkfs();

	XLGWFKFS getLatestGwfkfs();

	XLNWFKFS getLatestNwfkfs();
}
