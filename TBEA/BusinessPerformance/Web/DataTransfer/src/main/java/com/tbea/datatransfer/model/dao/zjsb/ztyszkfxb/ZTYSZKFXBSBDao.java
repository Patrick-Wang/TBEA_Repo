package com.tbea.datatransfer.model.dao.zjsb.ztyszkfxb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.ZTYSZKFXBBYQ;

public interface ZTYSZKFXBSBDao extends AbstractReadOnlyDao<ZTYSZKFXBBYQ> {

	public List<ZTYSZKFXBBYQ> getAllZTYSZKFXB();

}
