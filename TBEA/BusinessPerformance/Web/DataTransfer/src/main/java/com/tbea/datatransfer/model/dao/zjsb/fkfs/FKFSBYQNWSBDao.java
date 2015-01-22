package com.tbea.datatransfer.model.dao.zjsb.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjsb.FKFSBYQNWSB;
import com.tbea.datatransfer.model.entity.zjtb.FKFSBYQNWTB;

public interface FKFSBYQNWSBDao extends AbstractReadOnlyDao<FKFSBYQNWSB> {

	public List<FKFSBYQNWSB> getAllFKFSBYQNWSB();

}
