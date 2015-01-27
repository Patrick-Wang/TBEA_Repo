package com.tbea.datatransfer.model.dao.zjxl.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.FKFSXLNWXL;

public interface FKFSXLNWXLDao extends AbstractReadOnlyDao<FKFSXLNWXL> {

	public List<FKFSXLNWXL> getAllFKFSXLNW();

}
