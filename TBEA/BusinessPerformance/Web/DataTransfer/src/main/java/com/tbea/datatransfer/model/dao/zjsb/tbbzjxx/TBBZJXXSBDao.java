package com.tbea.datatransfer.model.dao.zjsb.tbbzjxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjsb.TBBZJXXSB;

public interface TBBZJXXSBDao extends AbstractReadOnlyDao<TBBZJXXSB> {

	public List<TBBZJXXSB> getAllTBBZJXXSB();

}
