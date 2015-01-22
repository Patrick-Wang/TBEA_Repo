package com.tbea.datatransfer.model.dao.zjsb.mrhk;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjsb.MRHKSB;
import com.tbea.datatransfer.model.entity.zjtb.MRHKTB;

public interface MRHKSBDao extends AbstractReadOnlyDao<MRHKSB> {

	public List<MRHKSB> getAllMRHKSB();
	
}
