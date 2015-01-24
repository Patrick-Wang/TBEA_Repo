package com.tbea.datatransfer.model.dao.zjtb.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQNWBYQ;

public interface FKFSBYQNWTBDao extends AbstractReadOnlyDao<FKFSBYQNWBYQ> {

	public List<FKFSBYQNWBYQ> getAllFKFSBYQNW();

}
