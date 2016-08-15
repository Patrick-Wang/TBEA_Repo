package com.tbea.ic.operation.model.dao.identifier.cpzlqk.pdbhglb;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.PdBhglbEntity;



public interface PdBhglbDao extends AbstractReadWriteDao<PdBhglbEntity> {

	List<PdBhglbEntity> getAll();

	PdBhglbEntity getByName(String name);

}
