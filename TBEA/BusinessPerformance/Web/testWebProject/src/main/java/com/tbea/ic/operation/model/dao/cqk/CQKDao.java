package com.tbea.ic.operation.model.dao.cqk;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;








import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.local.CQK;

public interface CQKDao extends AbstractReadWriteDao<com.tbea.ic.operation.model.entity.local.CQK> {

	List<CQK> getPreYearCQK(Date d, Company comp);

	List<CQK> getCurYearCQK(Date d, Company comp);

	List<CQK> getCqkData(Date d, Company comp);

	CQK getLatestCQK();

}
