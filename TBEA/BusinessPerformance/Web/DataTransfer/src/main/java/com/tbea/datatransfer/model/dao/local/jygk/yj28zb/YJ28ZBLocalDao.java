package com.tbea.datatransfer.model.dao.local.jygk.yj28zb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.jygk.YJ28ZBLocal;

public interface YJ28ZBLocalDao extends AbstractReadWriteDao<YJ28ZBLocal> {

	public List<YJ28ZBLocal> getAllYJ28ZBLocal();

	public void truncateYJ28ZBLocal();

	public void deleteYJ28ZBLocalByDW(List<Integer> dwidList);

}
