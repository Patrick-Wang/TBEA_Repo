package com.tbea.ic.operation.model.dao.jygk.zzy;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyCcKglyddcbqk;

public interface CcKglyddcbqkDao  extends AbstractReadWriteDao<JygkZzyCcKglyddcbqk>{
	Object[] getViewDataByq(String year, String month, String dwxxid);
	Object[] getViewDataXl(String year, String month, String dwxxid);
}
