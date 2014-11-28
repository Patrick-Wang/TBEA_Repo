package com.tbea.test.testWebProject.model.dao.yqysysfx;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.test.testWebProject.model.entity.YQYSYSFX;
import com.tbea.test.testWebProject.model.entity.local.YQK;

public interface YQYSYSFXDao extends AbstractReadWriteDao<YQYSYSFX> {

	List<YQYSYSFX> getYqysysfxList(Date d);

}
