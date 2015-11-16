package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbZbwcqk;

public interface FxJkcbZbwcqkDao extends AbstractReadWriteDao<JygkZzyFxJkcbZbwcqk> {
	public List<JygkZzyFxJkcbZbwcqk> getDataListByDwDate(int dwxxId,int nf,int yn);
	public JygkZzyFxJkcbZbwcqk readDataByDwFlData(int dwxxId,int fl,int nf,int yf);
		
}
