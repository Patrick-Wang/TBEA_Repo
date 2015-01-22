package com.tbea.datatransfer.model.dao.zjsb.yszktz;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjsb.YSZKTZSB;
import com.tbea.datatransfer.model.entity.zjtb.YSZKTZTB;

public interface YSZKTZSBDao extends AbstractReadOnlyDao<YSZKTZSB> {

	public List<YSZKTZSB> getAllYSZKTZSB();
	
}
