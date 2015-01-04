package com.tbea.datatransfer.model.dao.zjtb.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjtb.FKFSBYQFDWTB;

public interface FKFSBYQFDWTBDao extends AbstractReadOnlyDao<FKFSBYQFDWTB> {

	public List<FKFSBYQFDWTB> getAllFKFSBYQFDWTB();

}
