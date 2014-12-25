package com.tbea.test.testWebProject.model.dao.cb;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.model.entity.XMXX;

public interface XMXXDao {
	XMXX getXmxxByBh(String bh);
	boolean hasCompany(Company company);

}
