package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyCcCcwcqk;

public interface CcCcwcqkDao  extends AbstractReadWriteDao<JygkZzyCcCcwcqk>{
	List<JygkZzyCcCcwcqk> getDataListByDwDate(String dwxxs,int nf,int yf);
	List<Object[]> getSumDataListByDwData(int dwxxId,int nf);
	Object[] getViewData(Integer zbxxId, Date date, String dwxxs);
}
