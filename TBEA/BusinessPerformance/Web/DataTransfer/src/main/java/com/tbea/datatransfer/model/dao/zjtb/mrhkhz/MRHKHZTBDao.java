package com.tbea.datatransfer.model.dao.zjtb.mrhkhz;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjtb.MRHKHZTB;

public interface MRHKHZTBDao extends AbstractReadOnlyDao<MRHKHZTB> {

	public List<MRHKHZTB> getAllMRHKHZTB();

}
