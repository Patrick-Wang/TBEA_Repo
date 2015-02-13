package com.tbea.datatransfer.model.dao.zj.jygk.ndjhzb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zj.jygk.NDJHZB;

public interface NDJHZBDao extends AbstractReadOnlyDao<NDJHZB> {

	public List<NDJHZB> getAllNDJHZB();

}
