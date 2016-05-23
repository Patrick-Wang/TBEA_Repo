package com.tbea.ic.operation.service.xlfkfstj;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface XLFKFSTJService {

	String[][] getFdwData(Date d, Company comp);

	String[][] getGwData(Date d, Company comp);

	String[][] getNwData(Date d, Company comp);

	boolean containsCompany(Company comp);

	Date getLatestDate();

	String[][] getFdwData(Date d, List<Company> comps);

	String[][] getGwData(Date d, List<Company> comps);

	String[][] getNwData(Date d, List<Company> comps);

	List<Integer> getCompany();

}
