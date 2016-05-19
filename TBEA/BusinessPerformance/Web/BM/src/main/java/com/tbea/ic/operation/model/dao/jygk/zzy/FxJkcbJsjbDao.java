package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbJsjb;

public interface FxJkcbJsjbDao  extends AbstractReadWriteDao<JygkZzyFxJkcbJsjb>{
	public List<JygkZzyFxJkcbJsjb> getDataListByDwDate(String dwxxs,int nf,int yn);
}
