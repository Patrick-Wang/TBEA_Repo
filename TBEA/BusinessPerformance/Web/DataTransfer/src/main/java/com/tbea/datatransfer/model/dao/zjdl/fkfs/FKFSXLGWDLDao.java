package com.tbea.datatransfer.model.dao.zjdl.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjdl.FKFSXLGWDL;

public interface FKFSXLGWDLDao extends AbstractReadOnlyDao<FKFSXLGWDL> {

	public List<FKFSXLGWDL> getAllFKFSXLGWDL();

}
