package com.tbea.ic.operation.model.dao.cpzlqk.byqbhgwtmx;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.ByqBhgwtmxEntity;



public interface ByqBhgwtmxDao extends AbstractReadWriteDao<ByqBhgwtmxEntity> {

	List<ByqBhgwtmxEntity> getByYd(Date d,  List<Integer> zts);

	List<ByqBhgwtmxEntity> getByJd(Date d,  List<Integer> zts);
	
	List<Object[]> getByYdFb(Date d,  List<Integer> zts);

	List<Object[]> getByJdFb(Date d,  List<Integer> zts);

	ByqBhgwtmxEntity getFirstBhgwtmx(Date d, Company company);

	List<ByqBhgwtmxEntity> getByDate(Date d, Company company);

	List<ByqBhgwtmxEntity> getByYd(Date d,  Company company,
			List<Integer> zts);

	List<ByqBhgwtmxEntity> getByJd(Date d,  Company company,
			List<Integer> zts);

	List<Object[]> getByYdFb(Date d,  Company company,
			List<Integer> zts);
	
	List<Object[]> getByJdFb(Date d,  Company company,
			List<Integer> zts);

	List<Object[]> getByJdFbHj(Date d,  List<Integer> zts);

	List<Object[]> getByYdFbHj(Date d,  List<Integer> zts);

	List<Object[]> getByYdFbHj(Date d,  Company company,  List<Integer> zts);

	List<Object[]> getByJdFbHj(Date d,  Company company,  List<Integer> zts);

}
