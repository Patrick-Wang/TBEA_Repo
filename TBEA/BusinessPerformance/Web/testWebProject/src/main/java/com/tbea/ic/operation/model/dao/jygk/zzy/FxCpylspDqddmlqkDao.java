package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxCpylspDqddmlqk;

public interface FxCpylspDqddmlqkDao extends AbstractReadWriteDao<JygkZzyFxCpylspDqddmlqk> {
	public List<JygkZzyFxCpylspDqddmlqk> getDataListByDwDate(int dwxxId,int nf,int yn);
	public JygkZzyFxCpylspDqddmlqk readDataByDwFlData(int dwxxId,int fl,int nf,int yf);
		
}
