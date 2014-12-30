package com.tbea.datatransfer.model.dao.zjtb.yszktz;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjtb.YSZKTZTB;

public interface YSZKTZTBDao extends AbstractReadOnlyDao<YSZKTZTB> {

	public List<YSZKTZTB> getAllYSZKTZTB();
	
}
