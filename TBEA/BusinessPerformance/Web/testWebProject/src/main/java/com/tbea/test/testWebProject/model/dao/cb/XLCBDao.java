package com.tbea.test.testWebProject.model.dao.cb;

import java.util.List;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.model.entity.CBXLTBDD;
import com.tbea.test.testWebProject.model.entity.CBXLWGDD;
import com.tbea.test.testWebProject.model.entity.CBXLZXDD;


public interface XLCBDao {
	List<CBXLTBDD> getTbdd();
	List<CBXLZXDD> getZxdd();
	List<CBXLWGDD> getWgdd();
	boolean containsTbCompany(Company comp);
}
