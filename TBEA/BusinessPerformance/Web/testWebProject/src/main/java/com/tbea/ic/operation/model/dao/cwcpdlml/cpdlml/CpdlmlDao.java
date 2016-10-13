package com.tbea.ic.operation.model.dao.cwcpdlml.cpdlml;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cwcpdlml.CpdlmlEntity;



public interface CpdlmlDao extends AbstractReadWriteDao<CpdlmlEntity> {

	CpdlmlEntity getByDate(Date d, Integer cpid, Company comp);

	CpdlmlEntity getSumByDate(Date d, Integer id, List<Company> comps);

}
