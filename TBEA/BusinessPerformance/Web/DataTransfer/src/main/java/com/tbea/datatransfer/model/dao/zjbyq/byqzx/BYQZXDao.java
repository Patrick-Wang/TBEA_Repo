package com.tbea.datatransfer.model.dao.zjbyq.byqzx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.BYQZX;

public interface BYQZXDao extends AbstractReadOnlyDao<BYQZX> {

	public List<BYQZX> getAllBYQZX();

}
