package com.tbea.ic.operation.service.yqysysfx;

import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface YQYSYSFXService {

	String[][] getYqysysfxData(Company comp);

	String[][] getYqysysfxData(List<Company> subComps);

}
