package com.tbea.ic.operation.model.dao.chgb.chzlbhqk;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.chgb.ChzlbhqkEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface ChzlbhqkDao extends AbstractReadWriteDao<ChzlbhqkEntity> {
	
	List<ChzlbhqkEntity> getByDate(Date ds, Date de, Company company);

}
