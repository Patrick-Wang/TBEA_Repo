package com.tbea.datatransfer.model.dao.zjbyq.tbbzjxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjbyq.TBBZJXXBYQ;

public interface TBBZJXXBYQDao extends AbstractReadOnlyDao<TBBZJXXBYQ> {

	public List<TBBZJXXBYQ> getAllTBBZJXX();

}
