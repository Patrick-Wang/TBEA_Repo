package com.tbea.datatransfer.model.dao.zjtb.yszktz;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.YSZKTZBYQ;

public interface YSZKTZTBDao extends AbstractReadOnlyDao<YSZKTZBYQ> {

	public List<YSZKTZBYQ> getAllYSZKTZ();
	
}
