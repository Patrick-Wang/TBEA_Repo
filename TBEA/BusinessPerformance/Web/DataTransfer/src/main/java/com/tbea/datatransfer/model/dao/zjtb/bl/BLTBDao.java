package com.tbea.datatransfer.model.dao.zjtb.bl;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.BLBYQ;

public interface BLTBDao extends AbstractReadOnlyDao<BLBYQ> {

	public List<BLBYQ> getAllBL();
	
}
