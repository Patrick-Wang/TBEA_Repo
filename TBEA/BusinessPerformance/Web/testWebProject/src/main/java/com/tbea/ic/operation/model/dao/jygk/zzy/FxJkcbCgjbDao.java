package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbCgjb;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;


public interface FxJkcbCgjbDao extends AbstractReadWriteDao<JygkZzyFxJkcbCgjb> {
	public List<JygkZzyFxJkcbCgjb> getDataListByDwDate(int dwxxId,int nf,int yn);
	public JygkZzyFxJkcbCgjb readDataByDwFlData(int dwxxId,int fl,int nf,int yf);
		
}
