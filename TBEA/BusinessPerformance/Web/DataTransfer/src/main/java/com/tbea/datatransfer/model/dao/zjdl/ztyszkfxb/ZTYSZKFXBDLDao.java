package com.tbea.datatransfer.model.dao.zjdl.ztyszkfxb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjxl.ZTYSZKFXBXL;

public interface ZTYSZKFXBDLDao extends AbstractReadOnlyDao<ZTYSZKFXBXL> {

	public List<ZTYSZKFXBXL> getAllZTYSZKFXB();
	
}
