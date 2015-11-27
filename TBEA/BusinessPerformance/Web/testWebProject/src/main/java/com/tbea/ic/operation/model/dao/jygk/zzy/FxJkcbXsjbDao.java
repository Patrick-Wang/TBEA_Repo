package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbXsjb;

public interface FxJkcbXsjbDao  extends AbstractReadWriteDao<JygkZzyFxJkcbXsjb>{
	List<Object[]> getViewDataList(String year, String month, String dwxxid);
}
