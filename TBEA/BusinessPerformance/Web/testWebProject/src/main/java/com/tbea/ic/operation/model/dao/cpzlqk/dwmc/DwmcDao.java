package com.tbea.ic.operation.model.dao.cpzlqk.dwmc;
import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.cpzlqk.DwmcEntity;



public interface DwmcDao extends AbstractReadWriteDao<DwmcEntity> {


	DwmcEntity getByDwid(Integer dwid);

}
