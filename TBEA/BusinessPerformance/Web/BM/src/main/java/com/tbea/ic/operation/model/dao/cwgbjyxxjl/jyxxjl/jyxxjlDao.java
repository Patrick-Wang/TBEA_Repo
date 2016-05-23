package com.tbea.ic.operation.model.dao.cwgbjyxxjl.jyxxjl;
import java.util.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cwgbjyxxjl.JyxxjlEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface jyxxjlDao extends AbstractReadWriteDao<JyxxjlEntity> {

	List<JyxxjlEntity> getByDate(Date ds, Date de, Company company);

	List<JyxxjlEntity> getByDate(Date d, Company company);
	
	List<JyxxjlEntity> getByDate(Date ds, Date de, Company company, Integer kmId);

	JyxxjlEntity getByDate(Date d, Company company, Integer kmId);
	
}
