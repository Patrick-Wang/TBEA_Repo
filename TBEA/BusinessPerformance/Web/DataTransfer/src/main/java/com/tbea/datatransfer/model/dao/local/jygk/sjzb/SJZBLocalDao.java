package com.tbea.datatransfer.model.dao.local.jygk.sjzb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.jygk.SJZBLocal;

public interface SJZBLocalDao extends AbstractReadWriteDao<SJZBLocal> {

	public List<SJZBLocal> getAllSJZBLocal();

	public void truncateSJZBLocal();

	public void deleteSJZBLocalByDW(List<Integer> dwidList);

	public void deleteSJZBLocalByDWAndDate(List<Integer> dwidList, int nf,
			int yf);

}
