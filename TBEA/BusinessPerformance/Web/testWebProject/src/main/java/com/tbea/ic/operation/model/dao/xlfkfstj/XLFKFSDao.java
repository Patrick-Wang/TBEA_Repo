package com.tbea.ic.operation.model.dao.xlfkfstj;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.XLFDWFKFS;
import com.tbea.ic.operation.model.entity.XLGWFKFS;
import com.tbea.ic.operation.model.entity.XLNWFKFS;

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

	List<XLFDWFKFS> getFdwfkfs(Date d, List<Company> comps);

	List<XLGWFKFS> getGwfkfs(Date d, List<Company> comps);

	List<XLNWFKFS> getNwfkfs(Date d, List<Company> comps);

	List<Integer> getCompany();
}
