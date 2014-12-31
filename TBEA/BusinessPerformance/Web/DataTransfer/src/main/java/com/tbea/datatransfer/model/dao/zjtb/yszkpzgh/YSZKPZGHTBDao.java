package com.tbea.datatransfer.model.dao.zjtb.yszkpzgh;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjtb.YSZKPZGHTB;

public interface YSZKPZGHTBDao extends AbstractReadOnlyDao<YSZKPZGHTB> {

	public List<YSZKPZGHTB> getAllYSZKPZGHTB();

}
