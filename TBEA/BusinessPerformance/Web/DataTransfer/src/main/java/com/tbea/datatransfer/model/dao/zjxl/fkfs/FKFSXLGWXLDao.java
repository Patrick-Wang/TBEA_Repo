package com.tbea.datatransfer.model.dao.zjxl.fkfs;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.FKFSXLGWXL;

public interface FKFSXLGWXLDao extends AbstractReadOnlyDao<FKFSXLGWXL> {

	public List<FKFSXLGWXL> getAllFKFSXLGW();

}
