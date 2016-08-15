package com.tbea.ic.operation.model.dao.identifier.cpzlqk.pdzrlb;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.PdZrlbEntity;



public interface PdZrlbDao extends AbstractReadWriteDao<PdZrlbEntity> {

	List<PdZrlbEntity> getAll();

	PdZrlbEntity getByName(String name);

}
