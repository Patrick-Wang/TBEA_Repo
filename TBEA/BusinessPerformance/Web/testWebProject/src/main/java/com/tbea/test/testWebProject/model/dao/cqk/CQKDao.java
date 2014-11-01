package com.tbea.test.testWebProject.model.dao.cqk;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.test.testWebProject.model.entity.CQK;
import com.tbea.test.testWebProject.model.entity.QYZJK;

public interface CQKDao extends AbstractReadWriteDao<com.tbea.test.testWebProject.model.entity.local.CQK> {

	List<CQK> getPreYearCQK(Date d);

	List<CQK> getCurYearCQK(Date d);

	List<CQK> getCqkData(Date d);

}
