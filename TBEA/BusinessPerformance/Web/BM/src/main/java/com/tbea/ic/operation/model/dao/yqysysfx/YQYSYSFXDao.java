package com.tbea.ic.operation.model.dao.yqysysfx;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.YQYSYSFX;

public interface YQYSYSFXDao extends AbstractReadWriteDao<YQYSYSFX> {

	List<YQYSYSFX> getYqysysfxList(Date d, Company comp);

	List<YQYSYSFX> getYqysysfxList(Date d, List<Company> comps);

}
