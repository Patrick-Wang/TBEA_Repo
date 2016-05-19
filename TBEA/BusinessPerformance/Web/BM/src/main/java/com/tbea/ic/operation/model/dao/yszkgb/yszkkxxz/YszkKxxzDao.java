package com.tbea.ic.operation.model.dao.yszkgb.yszkkxxz;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.yszkgb.YszkKxxzEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkYjtzTjqsEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface YszkKxxzDao extends AbstractReadWriteDao<YszkKxxzEntity> {

	List<YszkKxxzEntity> getByDate(Date ds, Date de, Company company);
	YszkKxxzEntity getByDate(Date d, Company company);
}
