package com.tbea.datatransfer.model.dao.zjdl.ztyszkfxb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjdl.ZTYSZKFXBDL;

public interface ZTYSZKFXBDLDao extends AbstractReadOnlyDao<ZTYSZKFXBDL> {

	public List<ZTYSZKFXBDL> getAllZTYSZKFXBDL();
	
}
