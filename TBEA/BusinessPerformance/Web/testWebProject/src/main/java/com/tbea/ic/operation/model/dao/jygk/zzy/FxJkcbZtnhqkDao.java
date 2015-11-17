package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbZtnhqk;

public interface FxJkcbZtnhqkDao  extends AbstractReadWriteDao<JygkZzyFxJkcbZtnhqk>{
	List<JygkZzyFxJkcbZtnhqk> getDataListByDwData(int dwxxId,int nf,int yf);
	List<Object[]> getSumDataListByDwData(int dwxxId,int nf);
}
