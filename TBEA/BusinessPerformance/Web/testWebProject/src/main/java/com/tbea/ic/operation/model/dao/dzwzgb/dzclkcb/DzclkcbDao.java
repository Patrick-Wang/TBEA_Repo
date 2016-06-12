package com.tbea.ic.operation.model.dao.dzwzgb.dzclkcb;
import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.dzwzgb.DzclkcbEntity;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;



public interface DzclkcbDao extends AbstractReadWriteDao<DzclkcbEntity> {

	List<DzclkcbEntity> getByNf(Date d, Company company);

	List<DzclkcbEntity> getByNy(Date d, Company company);

	DzclkcbEntity getByNy(Date d, Company company, Integer clid);

}
