package com.tbea.datatransfer.model.dao.zjtb.ztyszkfxb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjtb.ZTYSZKFXBTB;

public interface ZTYSZKFXBTBDao extends AbstractReadOnlyDao<ZTYSZKFXBTB> {

	public List<ZTYSZKFXBTB> getAllZTYSZKFXBTB();
	
}
