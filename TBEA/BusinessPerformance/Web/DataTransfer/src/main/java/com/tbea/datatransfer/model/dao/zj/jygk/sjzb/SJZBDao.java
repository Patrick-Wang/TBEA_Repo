package com.tbea.datatransfer.model.dao.zj.jygk.sjzb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zj.jygk.SJZB;

public interface SJZBDao extends AbstractReadOnlyDao<SJZB> {

	public List<SJZB> getAllSJZB();

	public List<SJZB> getNewSJZB(int nf, int yf);

}
