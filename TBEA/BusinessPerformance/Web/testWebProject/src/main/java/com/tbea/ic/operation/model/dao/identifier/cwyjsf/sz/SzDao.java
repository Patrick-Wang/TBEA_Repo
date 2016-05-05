package com.tbea.ic.operation.model.dao.identifier.cwyjsf.sz;
import java.util.List;

import com.tbea.ic.operation.model.entity.identifier.cwyjsf.SzEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface SzDao extends AbstractReadWriteDao<SzEntity> {

	List<SzEntity> getAll();

}
