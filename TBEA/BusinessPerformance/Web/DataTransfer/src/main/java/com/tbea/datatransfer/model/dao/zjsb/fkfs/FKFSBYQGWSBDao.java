package com.tbea.datatransfer.model.dao.zjsb.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjsb.FKFSBYQGWSB;
import com.tbea.datatransfer.model.entity.zjtb.FKFSBYQGWTB;

public interface FKFSBYQGWSBDao extends AbstractReadOnlyDao<FKFSBYQGWSB> {

	public List<FKFSBYQGWSB> getAllFKFSBYQGWSB();

}
