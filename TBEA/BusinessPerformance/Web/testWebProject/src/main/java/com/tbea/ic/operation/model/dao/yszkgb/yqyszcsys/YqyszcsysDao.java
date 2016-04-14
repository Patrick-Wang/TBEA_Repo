package com.tbea.ic.operation.model.dao.yszkgb.yqyszcsys;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.yszkgb.YqyszcsysEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkKxxzEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface YqyszcsysDao extends AbstractReadWriteDao<YqyszcsysEntity> {

	List<YqyszcsysEntity> getByDate(Date ds, Date de, Company company);
	YqyszcsysEntity getByDate(Date d, Company company);
}
