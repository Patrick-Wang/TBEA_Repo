package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbZtnhqk;

public interface FxJkcbZtnhqkDao  extends AbstractReadWriteDao<JygkZzyFxJkcbZtnhqk>{
	List<Object[]> getSumDataListByDwData(String dwxxId,int nf);
	
	List<Object[]> getViewDataListByq(String year, String month, String dwxxid);
	List<Object[]> getViewDataListXl(String year, String month, String dwxxid);
}
