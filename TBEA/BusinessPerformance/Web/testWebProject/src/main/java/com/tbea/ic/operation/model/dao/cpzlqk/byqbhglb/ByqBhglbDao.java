package com.tbea.ic.operation.model.dao.cpzlqk.byqbhglb;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.ByqBhglbEntity;



public interface ByqBhglbDao extends AbstractReadWriteDao<ByqBhglbEntity> {

	List<ByqBhglbEntity> getAll();

}
