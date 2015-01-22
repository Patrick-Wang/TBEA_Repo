package com.tbea.datatransfer.model.dao.zjsb.ydhkjhjgb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zjsb.YDHKJHJGBSB;
import com.tbea.datatransfer.model.entity.zjtb.YDHKJHJGBTB;

public interface YDHKJHJGBSBDao extends AbstractReadOnlyDao<YDHKJHJGBSB> {

	public List<YDHKJHJGBSB> getAllYDHKJHJGBSB();
	
}
