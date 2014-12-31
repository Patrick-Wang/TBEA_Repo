package com.tbea.datatransfer.model.dao.local.yszkpzgh;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.YSZKPZGHLocal;

public interface YSZKPZGHLocalDao extends AbstractReadWriteDao<YSZKPZGHLocal> {

	public List<YSZKPZGHLocal> getAllYSZKPZGHLocal();

	public void deleteYSZKPZGHLocalByQY(int qybh);

}
