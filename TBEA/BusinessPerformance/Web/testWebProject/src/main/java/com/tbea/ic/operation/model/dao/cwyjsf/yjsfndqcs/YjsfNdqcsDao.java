package com.tbea.ic.operation.model.dao.cwyjsf.yjsfndqcs;
import java.sql.Date;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cwyjsf.YjsfNdqcsEntity;
import com.tbea.ic.operation.model.entity.identifier.cwyjsf.SzEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface YjsfNdqcsDao extends AbstractReadWriteDao<YjsfNdqcsEntity> {

	YjsfNdqcsEntity getByDate(Date d, Company company, Integer sz);

}
