package com.tbea.datatransfer.model.dao.zjsb.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjsb.FKFSBYQFDWSB;

public interface FKFSBYQFDWSBDao extends AbstractReadOnlyDao<FKFSBYQFDWSB> {

	public List<FKFSBYQFDWSB> getAllFKFSBYQFDWSB();

}
