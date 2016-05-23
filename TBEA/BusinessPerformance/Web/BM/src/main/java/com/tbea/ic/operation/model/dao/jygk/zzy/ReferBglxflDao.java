package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglx;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglxfl;

public interface ReferBglxflDao extends AbstractReadWriteDao<JygkZzyDwReferBglxfl> {
	List<JygkZzyDwReferBglxfl> getDataList(int dwxxId,int bglxId);
}
