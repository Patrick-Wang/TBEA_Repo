package com.tbea.datatransfer.model.dao.zjtb.ydhkjhjgb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjtb.YDHKJHJGBTB;

public interface YDHKJHJGBTBDao extends AbstractReadOnlyDao<YDHKJHJGBTB> {

	public List<YDHKJHJGBTB> getAllYDHKJHJGBTB();
	
}
