package com.tbea.datatransfer.model.dao.zjdl.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjdl.FKFSXLFDWDL;

public interface FKFSXLFDWDLDao extends AbstractReadOnlyDao<FKFSXLFDWDL> {

	public List<FKFSXLFDWDL> getAllFKFSXLFDWDL();

}
