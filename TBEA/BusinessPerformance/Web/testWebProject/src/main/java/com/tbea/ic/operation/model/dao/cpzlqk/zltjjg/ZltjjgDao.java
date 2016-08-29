package com.tbea.ic.operation.model.dao.cpzlqk.zltjjg;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;



public interface ZltjjgDao extends AbstractReadWriteDao<ZltjjgEntity> {

	ZltjjgEntity getByDate(Date d, int cpid, Company company, ZBStatus zt);

	ZltjjgEntity getYearAcc(Date d, int cpid, Company company, ZBStatus zt);

	ZltjjgEntity getJdAcc(Date d, int cpid, Company company, ZBStatus zt);

	ZltjjgEntity getByDateTotal(Date d, List<Integer> cplist, Company company, ZBStatus zt) ;

	ZltjjgEntity getFirstTjjg(Date d, Company company);

	ZltjjgEntity getJdAccQntq(Date d, int cpid, Company company, ZBStatus zt);

	ZltjjgEntity getByDateIgnoreStatus(Date d, Integer cpid, Company company);

	ZltjjgEntity getByDate(Date d, int id, List<Integer> ids, ZBStatus zt);

	ZltjjgEntity getYearAcc(Date d, int id, List<Integer> ids, ZBStatus zt);

	ZltjjgEntity getJdAcc(Date d, int id, List<Integer> ids, ZBStatus zt);

	ZltjjgEntity getJdAccQntq(Date d, int id, List<Integer> ids,
			ZBStatus zt);

	ZltjjgEntity getByDateTotal(Date date, List<Integer> cpids,
			List<Integer> ids, ZBStatus zt);


}
