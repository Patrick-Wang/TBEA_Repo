package com.tbea.ic.operation.model.dao.identifier.cpzlqk.xlbhglb;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.identifier.cpzlqk.XlBhglbEntity;



public interface XlBhglbDao extends AbstractReadWriteDao<XlBhglbEntity> {

	XlBhglbEntity getByName(String name);

	List<XlBhglbEntity> getAll();

}
