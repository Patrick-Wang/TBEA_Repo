package com.tbea.datatransfer.model.dao.zjdl.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.FKFSXLNWXL;

public interface FKFSXLNWDLDao extends AbstractReadOnlyDao<FKFSXLNWXL> {

	public List<FKFSXLNWXL> getAllFKFSXLNW();

}
