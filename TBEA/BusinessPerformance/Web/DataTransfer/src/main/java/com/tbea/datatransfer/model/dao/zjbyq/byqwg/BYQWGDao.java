package com.tbea.datatransfer.model.dao.zjbyq.byqwg;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.BYQWG;

public interface BYQWGDao extends AbstractReadOnlyDao<BYQWG> {

	public List<BYQWG> getAllBYQWG();

}
