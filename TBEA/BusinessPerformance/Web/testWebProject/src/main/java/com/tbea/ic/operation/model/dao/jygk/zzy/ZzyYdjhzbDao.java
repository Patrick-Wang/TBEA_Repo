package com.tbea.ic.operation.model.dao.jygk.zzy;


import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.YDJHZB;

public interface ZzyYdjhzbDao  extends AbstractReadWriteDao<YDJHZB>{
	public List<YDJHZB> getDataListByDwDate(String dwxxs,String zbidstrs,int nf,int yf);
}
