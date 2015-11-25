package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbZbwcqk;

public interface FxJkcbZbwcqkDao extends AbstractReadWriteDao<JygkZzyFxJkcbZbwcqk> {
	public List<JygkZzyFxJkcbZbwcqk> getDataListByDwDate(String dwxxs,int nf,int yn);
		
}
