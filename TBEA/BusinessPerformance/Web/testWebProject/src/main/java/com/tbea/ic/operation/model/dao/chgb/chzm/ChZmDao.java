package com.tbea.ic.operation.model.dao.chgb.chzm;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.chgb.ChZmEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkzmEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface ChZmDao extends AbstractReadWriteDao<ChZmEntity> {

	List<ChZmEntity> getByDate(Date d, Company company);
}
