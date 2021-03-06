package com.tbea.ic.operation.model.dao.pricelib.jcycljg.jkzj;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.JkzjEntity;


public interface JkzjDao extends GetEntitiesDao<JkzjEntity>, AbstractReadWriteDao<JkzjEntity>{

	JkzjEntity getByDate(Date date);

}
