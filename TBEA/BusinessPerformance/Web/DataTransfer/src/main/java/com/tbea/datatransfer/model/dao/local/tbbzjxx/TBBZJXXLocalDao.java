package com.tbea.datatransfer.model.dao.local.tbbzjxx;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.TBBZJXXLocal;

public interface TBBZJXXLocalDao extends AbstractReadWriteDao<TBBZJXXLocal> {

	public List<TBBZJXXLocal> getAllTBBZJXXLocal();

	public void deleteTBBZJXXLocalByQY(int qybh);

}
