package com.tbea.ic.operation.model.dao.cpzlqk.byqbhgwtmx;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.model.entity.cpzlqk.ByqBhgwtmxEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface ByqBhgwtmxDao extends AbstractReadWriteDao<ByqBhgwtmxEntity> {

	List<ByqBhgwtmxEntity> getByYd(Date d, int tjfs);

	List<ByqBhgwtmxEntity> getByJd(Date d, int tjfs);
	
	List<Object[]> getByYdFb(Date d, int tjfs);

	List<Object[]> getByJdFb(Date d, int tjfs);

}
