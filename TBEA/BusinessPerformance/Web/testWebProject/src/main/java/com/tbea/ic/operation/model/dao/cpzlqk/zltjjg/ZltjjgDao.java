package com.tbea.ic.operation.model.dao.cpzlqk.zltjjg;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;



public interface ZltjjgDao extends AbstractReadWriteDao<ZltjjgEntity> {

	ZltjjgEntity getByDate(Date d, int cpid, Company company);

	ZltjjgEntity getYearAcc(Date d, int cpid, Company company);

	ZltjjgEntity getJdAcc(Date d, int cpid, Company company);

	ZltjjgEntity getByDateTotal(Date d, List<Integer> cplist, Company company) ;

	ZltjjgEntity getFirstTjjg(Date d, Company company);

	ZltjjgEntity getJdAccQntq(Date d, int cpid, Company company);

}
