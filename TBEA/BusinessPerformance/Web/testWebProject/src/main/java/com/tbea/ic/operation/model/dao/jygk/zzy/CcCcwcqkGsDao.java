package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyCcCcwcqkGs;

public interface CcCcwcqkGsDao  extends AbstractReadWriteDao<JygkZzyCcCcwcqkGs>{
	Object[] getViewData(Integer zbxxId, Date date, String dwxxs);
}
