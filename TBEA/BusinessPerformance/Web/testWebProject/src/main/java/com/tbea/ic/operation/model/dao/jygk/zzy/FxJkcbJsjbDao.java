package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbJsjb;

public interface FxJkcbJsjbDao  extends AbstractReadWriteDao<JygkZzyFxJkcbJsjb>{
	JygkZzyFxJkcbJsjb getZb(Integer zb, Date date, int company);
	public List<JygkZzyFxJkcbJsjb> getDataListByDwData(int dwxxId,int nf,int yn);
	public JygkZzyFxJkcbJsjb readDataByDwFlData(int dwxxId,int fl,int nf,int yf);
}
