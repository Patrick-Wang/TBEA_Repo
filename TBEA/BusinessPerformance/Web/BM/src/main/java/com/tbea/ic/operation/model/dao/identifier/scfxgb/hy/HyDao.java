package com.tbea.ic.operation.model.dao.identifier.scfxgb.hy;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.identifier.scfxgb.HyEntity;



public interface HyDao extends AbstractReadWriteDao<HyEntity> {

	List<HyEntity> getAll();

}
