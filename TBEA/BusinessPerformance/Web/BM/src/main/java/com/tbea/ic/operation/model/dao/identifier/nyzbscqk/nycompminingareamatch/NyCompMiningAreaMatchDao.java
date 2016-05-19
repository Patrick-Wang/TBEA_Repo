package com.tbea.ic.operation.model.dao.identifier.nyzbscqk.nycompminingareamatch;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.nyzbscqk.NyCompMiningAreaMatchEntity;


public interface NyCompMiningAreaMatchDao extends AbstractReadWriteDao<NyCompMiningAreaMatchEntity> {
	
	List<NyCompMiningAreaMatchEntity> getMiningArea(Company company);
}
