package com.tbea.datatransfer.model.dao.local.jygk.yj20zb;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.jygk.YJ20ZBLocal;

public interface YJ20ZBLocalDao extends AbstractReadWriteDao<YJ20ZBLocal> {

	public List<YJ20ZBLocal> getAllYJ20ZBLocal();

	public void truncateYJ20ZBLocal();

	public void deleteYJ20ZBLocalByDW(List<Integer> dwidList);

}
