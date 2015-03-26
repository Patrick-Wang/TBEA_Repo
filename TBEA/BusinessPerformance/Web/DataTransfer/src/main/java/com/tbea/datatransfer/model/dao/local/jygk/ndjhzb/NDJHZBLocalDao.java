package com.tbea.datatransfer.model.dao.local.jygk.ndjhzb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.jygk.NDJHZBLocal;

public interface NDJHZBLocalDao extends AbstractReadWriteDao<NDJHZBLocal> {

	public List<NDJHZBLocal> getAllNDJHZBLocal();

	public void truncateNDJHZBLocal();

	public void deleteNDJHZBLocalByDW(List<Integer> dwidList);
	
	public void deleteNDJHZBLocalByDWAndDate(List<Integer> dwidList, int nf);

}
