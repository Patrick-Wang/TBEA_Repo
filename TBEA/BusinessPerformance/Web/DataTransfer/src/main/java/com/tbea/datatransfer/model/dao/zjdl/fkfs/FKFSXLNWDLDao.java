package com.tbea.datatransfer.model.dao.zjdl.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjdl.FKFSXLNWDL;

public interface FKFSXLNWDLDao extends AbstractReadOnlyDao<FKFSXLNWDL> {

	public List<FKFSXLNWDL> getAllFKFSXLNWDL();

}
