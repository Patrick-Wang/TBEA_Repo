package com.tbea.datatransfer.model.dao.zjsb.mrhkhz;

import java.util.List;

import com.tbea.datatransfer.model.entity.zjsb.MRHKHZSB;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;


public interface MRHKHZSBDao extends AbstractReadOnlyDao<MRHKHZSB> {

	public List<MRHKHZSB> getAllMRHKHZSB();

}
