package com.tbea.datatransfer.model.dao.zjtb.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjtb.FKFSBYQNWTB;

public interface FKFSBYQNWTBDao extends AbstractReadOnlyDao<FKFSBYQNWTB> {

	public List<FKFSBYQNWTB> getAllFKFSBYQNWTB();

}
