package com.tbea.ic.operation.model.dao.cwcpdlml.cpfl;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.cwcpdlml.CpflEntity;



public interface CpflDao extends AbstractReadWriteDao<CpflEntity> {

	List<CpflEntity> getCpflByCy(Integer cyId);

	List<CpflEntity> getAll();

}
