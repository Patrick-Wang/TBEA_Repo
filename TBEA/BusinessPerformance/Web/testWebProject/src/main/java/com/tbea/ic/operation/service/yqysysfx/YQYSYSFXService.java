package com.tbea.ic.operation.service.yqysysfx;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface YQYSYSFXService {

	String[][] getYqysysfxData(Date d, Company comp);

	String[][] getYqysysfxData(Date d, List<Company> subComps);

}
