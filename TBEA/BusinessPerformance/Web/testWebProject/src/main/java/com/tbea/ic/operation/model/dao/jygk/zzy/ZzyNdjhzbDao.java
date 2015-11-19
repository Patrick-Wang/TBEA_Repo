package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.NDJHZB;

public interface ZzyNdjhzbDao  extends AbstractReadWriteDao<NDJHZB>{
	public List<NDJHZB> getDataListByDwDate(int dwxxId,String zbidstrs,int nf);
	public NDJHZB readDataByDwFlData(int dwxxId,int zbid,int nf);
}
