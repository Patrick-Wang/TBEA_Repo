package com.tbea.ic.operation.model.dao.cb;

import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.CBXLTBDD;
import com.tbea.ic.operation.model.entity.CBXLWGDD;
import com.tbea.ic.operation.model.entity.CBXLZXDD;


public interface XLCBDao {
	List<CBXLTBDD> getTbdd();
	List<CBXLZXDD> getZxdd();
	List<CBXLWGDD> getWgdd();
	boolean containsTbCompany(Company comp);
	CBXLWGDD getLatestWgdd();
}
