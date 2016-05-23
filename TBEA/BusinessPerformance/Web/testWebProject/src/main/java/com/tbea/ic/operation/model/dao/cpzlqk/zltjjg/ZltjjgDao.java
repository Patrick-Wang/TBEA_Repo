package com.tbea.ic.operation.model.dao.cpzlqk.zltjjg;
import java.sql.Date;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface ZltjjgDao extends AbstractReadWriteDao<ZltjjgEntity> {

	ZltjjgEntity getByDate(Date d, int cpid, Company company);

	ZltjjgEntity getYearAcc(Date d, int cpid, Company company);

	ZltjjgEntity getJdAcc(Date d, int cpid, Company company);

	ZltjjgEntity getJdAccQntq(Date d, int cpid, Company company);

}
