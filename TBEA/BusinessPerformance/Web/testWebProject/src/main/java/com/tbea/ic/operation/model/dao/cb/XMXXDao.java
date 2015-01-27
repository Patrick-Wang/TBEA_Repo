package com.tbea.ic.operation.model.dao.cb;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.XMXX;

public interface XMXXDao {
	XMXX getXmxxByBh(String bh);
	boolean hasCompany(Company company);

}
