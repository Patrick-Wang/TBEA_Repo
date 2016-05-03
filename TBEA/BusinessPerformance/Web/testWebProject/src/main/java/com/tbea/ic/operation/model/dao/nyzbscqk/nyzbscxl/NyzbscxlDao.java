package com.tbea.ic.operation.model.dao.nyzbscqk.nyzbscxl;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.nyzbscqk.NyCompMiningAreaMatchEntity;
import com.tbea.ic.operation.model.entity.nyzbscqk.NyzbscxlEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface NyzbscxlDao extends AbstractReadWriteDao<NyzbscxlEntity> {
	
	List<NyCompMiningAreaMatchEntity> getMiningArea(Company company);

	List<NyzbscxlEntity> getByDate(Date d, Company company);

	List<NyzbscxlEntity> getByYear(Date d, Company company, int kq, int mz);

	NyCompMiningAreaMatchEntity getNyCompMiningAreaMatchEntityById(Integer id);
 
	NyzbscxlEntity getByDate(Date d, Company company, int kq, int mz);

}
