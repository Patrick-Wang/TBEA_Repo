package com.tbea.ic.operation.model.dao.pricelib.jcycljg.zhb;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.ZhbEntity;


public interface ZhbDao extends GetEntitiesDao<ZhbEntity>, AbstractReadWriteDao<ZhbEntity>{

	ZhbEntity getByDate(Date date);

}
