package com.tbea.ic.operation.model.dao.yszkgb.yszkzl;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.yszkgb.YszkZlEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkzmEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface YszkZlDao extends AbstractReadWriteDao<YszkZlEntity> {

	List<YszkZlEntity> getByDate(Date ds, Date de, Company company);

}
