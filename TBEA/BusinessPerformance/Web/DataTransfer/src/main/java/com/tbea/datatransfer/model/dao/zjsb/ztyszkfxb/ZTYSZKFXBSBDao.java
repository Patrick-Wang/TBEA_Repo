package com.tbea.datatransfer.model.dao.zjsb.ztyszkfxb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjsb.ZTYSZKFXBSB;
import com.tbea.datatransfer.model.entity.zjtb.ZTYSZKFXBTB;

public interface ZTYSZKFXBSBDao extends AbstractReadOnlyDao<ZTYSZKFXBSB> {

	public List<ZTYSZKFXBSB> getAllZTYSZKFXBSB();
	
}
