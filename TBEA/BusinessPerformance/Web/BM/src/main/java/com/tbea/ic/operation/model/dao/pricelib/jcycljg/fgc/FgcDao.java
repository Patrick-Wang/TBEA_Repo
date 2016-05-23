package com.tbea.ic.operation.model.dao.pricelib.jcycljg.fgc;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.DmdjyxEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.FgcEntity;


public interface FgcDao extends GetEntitiesDao<FgcEntity>, AbstractReadWriteDao<FgcEntity> {

	FgcEntity getByDate(Date date);

}
