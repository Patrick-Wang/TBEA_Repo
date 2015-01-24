package com.tbea.datatransfer.model.dao.zjsb.tbbzjxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.TBBZJXXBYQ;

public interface TBBZJXXSBDao extends AbstractReadOnlyDao<TBBZJXXBYQ> {

	public List<TBBZJXXBYQ> getAllTBBZJXX();

}
