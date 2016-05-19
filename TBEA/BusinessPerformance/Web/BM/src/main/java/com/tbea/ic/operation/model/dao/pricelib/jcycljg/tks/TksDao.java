package com.tbea.ic.operation.model.dao.pricelib.jcycljg.tks;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.TksEntity;


public interface TksDao extends GetEntitiesDao<TksEntity>, AbstractReadWriteDao<TksEntity>{

	TksEntity getByDate(Date date);

}
