package com.tbea.datatransfer.model.dao.zjdl.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.FKFSXLGWXL;

public interface FKFSXLGWDLDao extends AbstractReadOnlyDao<FKFSXLGWXL> {

	public List<FKFSXLGWXL> getAllFKFSXLGW();

}
