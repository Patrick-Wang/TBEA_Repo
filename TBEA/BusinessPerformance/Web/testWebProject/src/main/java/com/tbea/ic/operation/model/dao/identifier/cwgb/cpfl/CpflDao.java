package com.tbea.ic.operation.model.dao.identifier.cwgb.cpfl;
import java.util.List;

import com.tbea.ic.operation.model.entity.identifier.cwgb.CpflEntity;
import com.tbea.ic.operation.model.entity.identifier.cwgb.CyEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface CpflDao extends AbstractReadWriteDao<CpflEntity> {

	List<CyEntity> getByCy(int ordinal);

}
