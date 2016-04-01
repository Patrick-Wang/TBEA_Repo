package com.tbea.ic.operation.model.dao.pricelib.jcycljg.pvcsz;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.PVCSzEntity;


public interface PVCSzDao extends GetEntitiesDao<PVCSzEntity>, AbstractReadWriteDao<PVCSzEntity>{

	PVCSzEntity getByDate(Date date);

}
