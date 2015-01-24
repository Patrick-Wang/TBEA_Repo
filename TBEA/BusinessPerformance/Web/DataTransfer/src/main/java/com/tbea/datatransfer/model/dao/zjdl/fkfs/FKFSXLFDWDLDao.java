package com.tbea.datatransfer.model.dao.zjdl.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.FKFSXLFDWXL;

public interface FKFSXLFDWDLDao extends AbstractReadOnlyDao<FKFSXLFDWXL> {

	public List<FKFSXLFDWXL> getAllFKFSXLFDW();

}
