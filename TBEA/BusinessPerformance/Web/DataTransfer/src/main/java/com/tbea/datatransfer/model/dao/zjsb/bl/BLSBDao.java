package com.tbea.datatransfer.model.dao.zjsb.bl;

import java.util.List;

import com.tbea.datatransfer.model.entity.zjsb.BLSB;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;


public interface BLSBDao extends AbstractReadOnlyDao<BLSB> {

	public List<BLSB> getAllBLSB();
	
}
