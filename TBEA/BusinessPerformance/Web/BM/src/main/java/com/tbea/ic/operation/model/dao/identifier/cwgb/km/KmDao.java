package com.tbea.ic.operation.model.dao.identifier.cwgb.km;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.identifier.cwgb.KmEntity;



public interface KmDao extends AbstractReadWriteDao<KmEntity> {

	KmEntity getById(Integer ordinal);

	List<KmEntity> getAll();
}
