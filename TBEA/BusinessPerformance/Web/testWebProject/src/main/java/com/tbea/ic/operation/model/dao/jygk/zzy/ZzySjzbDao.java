package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.SJZB;

public interface ZzySjzbDao  extends AbstractReadWriteDao<SJZB>{
	
	public List<SJZB> getDataListByDwDate(String dwxxs,String zbidstrs,int nf,int yf);
	public List<SJZB> readDataLjByDwFlDate(String dwxxs,int zbid,int nf,int yf);
	public List<SJZB> readDataQnByDwFlDate(String dwxxs,int zbid,int nf);
	public SJZB readDataByDwFlDate(String dwxxs,int zbid,int nf,int yf);
}
