package com.tbea.datatransfer.model.dao.zjbyq.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQGWBYQ;

public interface FKFSBYQGWBYQDao extends AbstractReadOnlyDao<FKFSBYQGWBYQ> {

	public List<FKFSBYQGWBYQ> getAllFKFSBYQGW();

}
