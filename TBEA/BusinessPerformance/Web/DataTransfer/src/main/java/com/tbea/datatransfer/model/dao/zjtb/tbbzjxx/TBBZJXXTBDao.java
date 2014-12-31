package com.tbea.datatransfer.model.dao.zjtb.tbbzjxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjtb.TBBZJXXTB;

public interface TBBZJXXTBDao extends AbstractReadOnlyDao<TBBZJXXTB> {

	public List<TBBZJXXTB> getAllTBBZJXXTB();

}
