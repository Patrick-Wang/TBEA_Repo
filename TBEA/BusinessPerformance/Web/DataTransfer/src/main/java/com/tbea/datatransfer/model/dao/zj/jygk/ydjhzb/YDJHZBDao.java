package com.tbea.datatransfer.model.dao.zj.jygk.ydjhzb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDao;

import com.tbea.datatransfer.model.entity.zj.jygk.YDJHZB;

public interface YDJHZBDao extends AbstractReadOnlyDao<YDJHZB> {

	public List<YDJHZB> getAllYDJHZB();

}
