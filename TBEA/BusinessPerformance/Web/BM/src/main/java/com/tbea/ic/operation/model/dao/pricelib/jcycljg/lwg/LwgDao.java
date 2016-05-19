package com.tbea.ic.operation.model.dao.pricelib.jcycljg.lwg;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.LwgEntity;


public interface LwgDao extends GetEntitiesDao<LwgEntity>, AbstractReadWriteDao<LwgEntity>{

	LwgEntity getByDate(Date date);

}
