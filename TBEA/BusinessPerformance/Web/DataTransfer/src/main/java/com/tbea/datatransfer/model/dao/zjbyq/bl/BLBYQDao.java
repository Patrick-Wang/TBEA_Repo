package com.tbea.datatransfer.model.dao.zjbyq.bl;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.BLBYQ;

public interface BLBYQDao extends AbstractReadOnlyDao<BLBYQ> {

	public List<BLBYQ> getAllBL();

}
