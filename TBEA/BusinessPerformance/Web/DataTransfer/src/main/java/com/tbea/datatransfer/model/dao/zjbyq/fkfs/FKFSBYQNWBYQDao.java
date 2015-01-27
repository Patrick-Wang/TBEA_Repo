package com.tbea.datatransfer.model.dao.zjbyq.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQNWBYQ;

public interface FKFSBYQNWBYQDao extends AbstractReadOnlyDao<FKFSBYQNWBYQ> {

	public List<FKFSBYQNWBYQ> getAllFKFSBYQNW();

}
