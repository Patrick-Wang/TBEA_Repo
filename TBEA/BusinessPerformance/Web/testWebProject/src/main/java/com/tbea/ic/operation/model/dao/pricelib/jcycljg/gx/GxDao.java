package com.tbea.ic.operation.model.dao.pricelib.jcycljg.gx;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GxEntity;


public interface GxDao extends GetEntitiesDao<GxEntity>, AbstractReadWriteDao<GxEntity> {

	GxEntity getByDate(Date date);

}
