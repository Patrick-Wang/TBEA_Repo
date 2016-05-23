package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglx;

public interface ReferBglxDao extends AbstractReadWriteDao<JygkZzyDwReferBglx> {
	public List<JygkZzyDwReferBglx> getDataList(List<Company> comps);
}
