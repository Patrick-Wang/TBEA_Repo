package com.tbea.ic.operation.model.dao.cpzlqk.byqbhgwtmx;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.ByqBhgwtmxEntity;



public interface ByqBhgwtmxDao extends AbstractReadWriteDao<ByqBhgwtmxEntity> {

	List<ByqBhgwtmxEntity> getByYd(Date d, int tjfs, ZBStatus zt);

	List<ByqBhgwtmxEntity> getByJd(Date d, int tjfs, ZBStatus zt);
	
	List<Object[]> getByYdFb(Date d, int tjfs, ZBStatus zt);

	List<Object[]> getByJdFb(Date d, int tjfs, ZBStatus zt);

	ByqBhgwtmxEntity getFirstBhgwtmx(Date d, Company company, int tjfs);

	List<ByqBhgwtmxEntity> getByDate(Date d, Company company, int tjfs);

	List<ByqBhgwtmxEntity> getByYd(Date d, int tjfs, Company company,
			ZBStatus zt);

	List<ByqBhgwtmxEntity> getByJd(Date d, int tjfs, Company company,
			ZBStatus zt);

	List<Object[]> getByYdFb(Date d, int tjfs, Company company,
			ZBStatus zt);
	
	List<Object[]> getByJdFb(Date d, int tjfs, Company company,
			ZBStatus zt);

}
