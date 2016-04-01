package com.tbea.ic.operation.model.dao.pricelib.jcycljg.jt;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.JtEntity;


public interface JtDao extends GetEntitiesDao<JtEntity>, AbstractReadWriteDao<JtEntity>{

	JtEntity getByDate(Date date);

}
