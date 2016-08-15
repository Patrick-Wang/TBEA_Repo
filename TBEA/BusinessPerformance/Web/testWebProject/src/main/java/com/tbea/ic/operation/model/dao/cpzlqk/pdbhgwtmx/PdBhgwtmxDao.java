package com.tbea.ic.operation.model.dao.cpzlqk.pdbhgwtmx;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.PdBhgwtmxEntity;



public interface PdBhgwtmxDao extends AbstractReadWriteDao<PdBhgwtmxEntity> {

	List<PdBhgwtmxEntity> getByYd(Date d,  ZBStatus zt);

	List<PdBhgwtmxEntity> getByJd(Date d,  ZBStatus zt);
	
	List<Object[]> getByYdFb(Date d,  ZBStatus zt);

	List<Object[]> getByJdFb(Date d,  ZBStatus zt);

	PdBhgwtmxEntity getFirstBhgwtmx(Date d, Company company);

	List<PdBhgwtmxEntity> getByDate(Date d, Company company);

	List<PdBhgwtmxEntity> getByYd(Date d,  Company company,
			ZBStatus zt);

	List<PdBhgwtmxEntity> getByJd(Date d,  Company company,
			ZBStatus zt);

	List<Object[]> getByYdFb(Date d,  Company company,
			ZBStatus zt);
	
	List<Object[]> getByJdFb(Date d,  Company company,
			ZBStatus zt);

	List<Object[]> getByJdFbHj(Date d,  ZBStatus zt);

	List<Object[]> getByYdFbHj(Date d,  ZBStatus zt);

	List<Object[]> getByYdFbHj(Date d,  Company company, ZBStatus zt);

	List<Object[]> getByJdFbHj(Date d,  Company company, ZBStatus zt);

}
