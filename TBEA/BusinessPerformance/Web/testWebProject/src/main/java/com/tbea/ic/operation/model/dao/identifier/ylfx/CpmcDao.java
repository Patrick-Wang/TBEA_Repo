package com.tbea.ic.operation.model.dao.identifier.ylfx;
import com.tbea.ic.operation.model.entity.identifier.common.CpmcEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface CpmcDao extends AbstractReadWriteDao<CpmcEntity> {

	CpmcEntity getById(Integer id);
}
