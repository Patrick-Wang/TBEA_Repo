package com.tbea.datatransfer.model.dao.local.jygk.ydjhzb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.jygk.YDJHZBLocal;

public interface YDJHZBLocalDao extends AbstractReadWriteDao<YDJHZBLocal> {

	public List<YDJHZBLocal> getAllYDJHZBLocal();

	public void truncateYDJHZBLocal();

	public void deleteYDJHZBLocalByDW(List<Integer> dwidList);

}
