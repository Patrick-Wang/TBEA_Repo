package com.tbea.test.testWebProject.service.xlfkfstj;

import java.sql.Date;

import com.tbea.test.testWebProject.common.companys.Company;

public interface XLFKFSTJService {

	String[][] getFdwData(Date d, Company comp);

	String[][] getGwData(Date d, Company comp);

	String[][] getNwData(Date d, Company comp);

	boolean containsCompany(Company comp);

	Date getLatestDate();

}
