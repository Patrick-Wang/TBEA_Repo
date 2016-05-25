package com.tbea.ic.operation.model.dao.identifier.cpzlqk.byqzrlb;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.ByqZrlbEntity;



public interface ByqZrlbDao extends AbstractReadWriteDao<ByqZrlbEntity> {

	List<ByqZrlbEntity> getAll();

	ByqZrlbEntity getByName(String name);

}
