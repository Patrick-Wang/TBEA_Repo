package com.tbea.ic.operation.model.dao.nyzbscqk.nyzbscxl;
import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.nyzbscqk.NyzbscxlEntity;



public interface NyzbscxlDao extends AbstractReadWriteDao<NyzbscxlEntity> {
	
	List<NyzbscxlEntity> getByDate(Date d, Company company);

	List<NyzbscxlEntity> getByYear(Date d, Company company, int kq, int mz);

	NyzbscxlEntity getByDate(Date d, Company company, int kq, int mz);

}
