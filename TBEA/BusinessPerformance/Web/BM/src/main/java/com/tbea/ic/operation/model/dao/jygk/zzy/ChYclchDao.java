package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyChYclch;

public interface ChYclchDao  extends AbstractReadWriteDao<JygkZzyChYclch>{
	List<Object[]> getViewDataList(String year, String month, String dwxxid);
}
