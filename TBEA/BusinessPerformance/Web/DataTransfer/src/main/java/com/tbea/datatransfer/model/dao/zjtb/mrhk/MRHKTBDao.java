package com.tbea.datatransfer.model.dao.zjtb.mrhk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjtb.MRHKTB;

public interface MRHKTBDao extends AbstractReadOnlyDao<MRHKTB> {

	public List<MRHKTB> getAllMRHKTB();
	
}
