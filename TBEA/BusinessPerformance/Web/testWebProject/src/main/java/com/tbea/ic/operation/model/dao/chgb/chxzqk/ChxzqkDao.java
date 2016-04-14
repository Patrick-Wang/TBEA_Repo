package com.tbea.ic.operation.model.dao.chgb.chxzqk;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.chgb.ChxzqkEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface ChxzqkDao extends AbstractReadWriteDao<ChxzqkEntity> {

	List<ChxzqkEntity> getByDate(Date ds, Date de, Company company);
	
}
