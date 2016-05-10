package com.tbea.ic.operation.model.dao.cwcpdlml.cydw;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cwcpdlml.CyDwEntity;



public interface CyDwDao extends AbstractReadWriteDao<CyDwEntity> {

	CyDwEntity getByDw(Company company);

}
