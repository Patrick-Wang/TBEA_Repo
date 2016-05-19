package com.tbea.ic.operation.model.dao.nyzbscqk.nyzbscjg;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.nyzbscqk.NyzbscjgEntity;



public interface NyzbscjgDao extends AbstractReadWriteDao<NyzbscjgEntity> {

	List<NyzbscjgEntity> getByYear(Date d, Company company, int kq, int mz);

	List<NyzbscjgEntity> getByDate(Date d, Company company);

	NyzbscjgEntity getByDate(Date d, Company company, int kq, int mz);

}
