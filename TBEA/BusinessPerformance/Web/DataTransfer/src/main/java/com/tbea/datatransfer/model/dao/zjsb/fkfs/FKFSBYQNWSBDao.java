package com.tbea.datatransfer.model.dao.zjsb.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.FKFSBYQNWBYQ;

public interface FKFSBYQNWSBDao extends AbstractReadOnlyDao<FKFSBYQNWBYQ> {

	public List<FKFSBYQNWBYQ> getAllFKFSBYQNW();

}
