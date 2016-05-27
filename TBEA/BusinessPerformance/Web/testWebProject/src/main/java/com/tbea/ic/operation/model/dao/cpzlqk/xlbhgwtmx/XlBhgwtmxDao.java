package com.tbea.ic.operation.model.dao.cpzlqk.xlbhgwtmx;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.XlBhgwtmxEntity;



public interface XlBhgwtmxDao extends AbstractReadWriteDao<XlBhgwtmxEntity> {

	List<XlBhgwtmxEntity> getByDate(Date d, ZBStatus zt);

	XlBhgwtmxEntity getFirstBhgwtmx(Date d, Company company);

	List<XlBhgwtmxEntity> getByDate(Date d, Company company);

}
