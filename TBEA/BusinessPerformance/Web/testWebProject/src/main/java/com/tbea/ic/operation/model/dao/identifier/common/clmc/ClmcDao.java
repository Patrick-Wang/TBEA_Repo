package com.tbea.ic.operation.model.dao.identifier.common.clmc;
import com.tbea.ic.operation.model.entity.identifier.common.ClmcEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface ClmcDao extends AbstractReadWriteDao<ClmcEntity> {

	ClmcEntity getById(Integer id);
}
