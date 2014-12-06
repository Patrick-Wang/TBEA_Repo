package com.tbea.test.testWebProject.model.dao.cqk;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;






import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.model.entity.local.CQK;

public interface CQKDao extends AbstractReadWriteDao<com.tbea.test.testWebProject.model.entity.local.CQK> {

	List<CQK> getPreYearCQK(Date d, Company comp);

	List<CQK> getCurYearCQK(Date d, Company comp);

	List<CQK> getCqkData(Date d, Company comp);

}
