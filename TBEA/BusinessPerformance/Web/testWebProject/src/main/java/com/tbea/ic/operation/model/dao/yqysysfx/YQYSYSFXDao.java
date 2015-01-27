package com.tbea.ic.operation.model.dao.yqysysfx;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.YQYSYSFX;
import com.tbea.ic.operation.model.entity.local.YQK;

public interface YQYSYSFXDao extends AbstractReadWriteDao<YQYSYSFX> {

	List<YQYSYSFX> getYqysysfxList();

}
