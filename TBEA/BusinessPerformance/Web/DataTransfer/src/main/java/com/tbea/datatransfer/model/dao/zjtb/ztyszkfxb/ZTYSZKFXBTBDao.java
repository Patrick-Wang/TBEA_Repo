package com.tbea.datatransfer.model.dao.zjtb.ztyszkfxb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.ZTYSZKFXBBYQ;

public interface ZTYSZKFXBTBDao extends AbstractReadOnlyDao<ZTYSZKFXBBYQ> {

	public List<ZTYSZKFXBBYQ> getAllZTYSZKFXB();
	
}
