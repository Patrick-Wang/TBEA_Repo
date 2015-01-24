package com.tbea.datatransfer.model.dao.zjbyq.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQFDWBYQ;

public interface FKFSBYQFDWBYQDao extends AbstractReadOnlyDao<FKFSBYQFDWBYQ> {

	public List<FKFSBYQFDWBYQ> getAllFKFSBYQFDW();

}
