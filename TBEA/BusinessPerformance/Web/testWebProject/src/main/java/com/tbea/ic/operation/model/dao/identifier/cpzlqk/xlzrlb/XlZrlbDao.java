package com.tbea.ic.operation.model.dao.identifier.cpzlqk.xlzrlb;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.XlZrlbEntity;



public interface XlZrlbDao extends AbstractReadWriteDao<XlZrlbEntity> {

	XlZrlbEntity getByName(String name);

	List<XlZrlbEntity> getAll();

}
