package com.tbea.ic.operation.model.dao.cpzlqk.xlbhgwtmx;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.model.entity.cpzlqk.XlBhgwtmxEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface XlBhgwtmxDao extends AbstractReadWriteDao<XlBhgwtmxEntity> {

	List<XlBhgwtmxEntity> getByDate(Date d);

}
