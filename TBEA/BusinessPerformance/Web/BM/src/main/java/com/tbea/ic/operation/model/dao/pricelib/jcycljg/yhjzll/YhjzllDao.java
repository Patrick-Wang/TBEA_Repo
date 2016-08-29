package com.tbea.ic.operation.model.dao.pricelib.jcycljg.yhjzll;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YhjzllEntity;


public interface YhjzllDao extends GetEntitiesDao<YhjzllEntity>, AbstractReadWriteDao<YhjzllEntity>{

	YhjzllEntity getByDate(Date date);

	List<?> getEntities();

}
