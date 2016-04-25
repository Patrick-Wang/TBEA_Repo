package com.tbea.ic.operation.model.dao.cbfx.nymyywmlfx;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cbfx.NymyywmlfxEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface NymyywmlfxDao extends AbstractReadWriteDao<NymyywmlfxEntity> {

	List<NymyywmlfxEntity> getByDate(Date d, Company company);

}
