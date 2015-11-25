package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxSxfykz;

public interface FxSxfykzDao extends AbstractReadWriteDao<JygkZzyFxSxfykz> {
	public List<JygkZzyFxSxfykz> getDataListByDwDate(String dwxxs,int nf,int yn);
		
}
