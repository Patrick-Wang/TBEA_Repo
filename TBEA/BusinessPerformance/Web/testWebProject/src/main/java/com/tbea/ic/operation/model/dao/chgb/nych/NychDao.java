package com.tbea.ic.operation.model.dao.chgb.nych;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.chgb.NychEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface NychDao extends AbstractReadWriteDao<NychEntity> {

	List<NychEntity> getByDate(Date ds, Date de, Company company);
	NychEntity getQCJYByDate(Date dSelected, Company company);
}
