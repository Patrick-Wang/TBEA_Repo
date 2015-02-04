package com.tbea.datatransfer.model.dao.local.yqysysfl;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.datatransfer.model.entity.local.YQYSYSFLLocal;

public interface YQYSYSFLLocalDao extends AbstractReadWriteDao<YQYSYSFLLocal> {

	public List<YQYSYSFLLocal> getAllYQYSYSFLLocal();

	public void deleteYQYSYSFLLocalByQY(int qybh);

}
