package com.tbea.ic.operation.model.dao.pricelib.jcycljg.eva;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.DmdjyxEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.EVAEntity;


public interface EVADao  extends GetEntitiesDao<EVAEntity>, AbstractReadWriteDao<EVAEntity>{

	EVAEntity getByDate(Date date);

}
