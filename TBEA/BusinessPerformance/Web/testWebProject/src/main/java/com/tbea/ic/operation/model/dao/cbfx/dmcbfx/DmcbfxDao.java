package com.tbea.ic.operation.model.dao.cbfx.dmcbfx;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cbfx.DmcbfxEntity;



public interface DmcbfxDao extends AbstractReadWriteDao<DmcbfxEntity> {

	List<DmcbfxEntity> getByDate(Date d, Company company);

}
