package com.tbea.ic.operation.model.dao.yqysysfx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.YQYSYSFX;

public interface YQYSYSFXDao extends AbstractReadWriteDao<YQYSYSFX> {

	List<YQYSYSFX> getYqysysfxList(Company comp);

	List<YQYSYSFX> getYqysysfxList(List<Company> comps);

}
