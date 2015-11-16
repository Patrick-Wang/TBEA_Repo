package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;


import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxCpylspHqlyddzl;

public interface FxCpylspHqlyddzlDao extends AbstractReadWriteDao<JygkZzyFxCpylspHqlyddzl> {
	public List<JygkZzyFxCpylspHqlyddzl> getDataListByDwDate(int dwxxId,int nf,int yn);
	public JygkZzyFxCpylspHqlyddzl readDataByDwFlData(int dwxxId,int fl,int nf,int yf);
		
}
