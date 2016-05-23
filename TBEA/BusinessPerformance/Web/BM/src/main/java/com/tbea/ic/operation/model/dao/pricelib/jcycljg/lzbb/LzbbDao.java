package com.tbea.ic.operation.model.dao.pricelib.jcycljg.lzbb;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.LzbbEntity;


public interface LzbbDao extends GetEntitiesDao<LzbbEntity>, AbstractReadWriteDao<LzbbEntity>{

	LzbbEntity getByDate(Date date);

}
