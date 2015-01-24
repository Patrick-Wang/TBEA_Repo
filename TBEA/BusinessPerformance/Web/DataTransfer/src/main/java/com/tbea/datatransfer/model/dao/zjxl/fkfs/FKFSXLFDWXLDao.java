package com.tbea.datatransfer.model.dao.zjxl.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.FKFSXLFDWXL;

public interface FKFSXLFDWXLDao extends AbstractReadOnlyDao<FKFSXLFDWXL> {

	public List<FKFSXLFDWXL> getAllFKFSXLFDW();

}
