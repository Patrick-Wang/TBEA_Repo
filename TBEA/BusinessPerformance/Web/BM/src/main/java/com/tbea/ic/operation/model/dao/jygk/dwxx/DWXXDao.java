package com.tbea.ic.operation.model.dao.jygk.dwxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.DWXX;

public interface DWXXDao extends AbstractReadWriteDao<DWXX> {
	List<DWXX> getDwxxs(List<Company> comps);

	DWXX getByName(String compName);
}
