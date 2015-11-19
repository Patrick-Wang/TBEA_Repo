package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.SJZB;

public interface ZzySjzbDao  extends AbstractReadWriteDao<SJZB>{
	
	public List<SJZB> getDataListByDwDate(int dwxxId,String zbidstrs,int nf,int yf);
	public SJZB readDataByDwFlData(int dwxxId,int zbid,int nf,int yf);
	public List<SJZB> readDataLjByDwFlData(int dwxxId,int zbid,int nf,int yf);
	public List<SJZB> readDataQnByDwFlData(int dwxxId,int zbid,int nf);
}
