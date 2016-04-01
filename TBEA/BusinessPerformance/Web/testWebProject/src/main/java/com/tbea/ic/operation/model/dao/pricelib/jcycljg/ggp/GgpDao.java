package com.tbea.ic.operation.model.dao.pricelib.jcycljg.ggp;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GgpEntity;


public interface GgpDao  extends GetEntitiesDao<GgpEntity>, AbstractReadWriteDao<GgpEntity>{

	GgpEntity getByDate(Date date);

}
