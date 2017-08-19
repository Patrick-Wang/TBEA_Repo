package com.tbea.ic.operation.model.dao.identifier.cpzlqk.xkzrlb;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.XkZrlbEntity;



public interface XkZrlbDao extends AbstractReadWriteDao<XkZrlbEntity> {

	List<XkZrlbEntity> getAll();

	XkZrlbEntity getByName(String name);

}
