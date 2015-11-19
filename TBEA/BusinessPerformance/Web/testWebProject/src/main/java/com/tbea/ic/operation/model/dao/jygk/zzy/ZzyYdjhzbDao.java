package com.tbea.ic.operation.model.dao.jygk.zzy;


import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;


import com.tbea.ic.operation.model.entity.jygk.YDJHZB;

public interface ZzyYdjhzbDao  extends AbstractReadWriteDao<YDJHZB>{
	public List<YDJHZB> getDataListByDwDate(int dwxxId,String zbidstrs,int nf,int yf);
	public YDJHZB readDataByDwFlData(int dwxxId,int zbid,int nf,int yf);
}
