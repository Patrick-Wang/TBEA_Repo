package com.tbea.ic.operation.model.dao.wgcpqk.yclbfqk;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.wgcpqk.YclbfqkEntity;



public interface YclbfqkDao extends AbstractReadWriteDao<YclbfqkEntity> {

	List<YclbfqkEntity> getByDate(Date date, Company company);

	YclbfqkEntity getByDate(Date d, Company company, Integer valueOf);

	List<YclbfqkEntity> getSumByDate(Date date, List<Company> subCompanies);

}
