package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbCgjb;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;


public interface FxJkcbCgjbDao extends AbstractReadWriteDao<JygkZzyFxJkcbCgjb> {
	public List<JygkZzyFxJkcbCgjb> getDataListByDwDate(String dwxxs,int nf,int yn);
}
