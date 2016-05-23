package com.tbea.ic.operation.model.dao.yszkgb.yszkzm;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.yszkgb.YszkzmEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface YszkzmDao extends AbstractReadWriteDao<YszkzmEntity> {

	YszkzmEntity getByDate(Date d, Company company);

}
