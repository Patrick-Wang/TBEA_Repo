package com.tbea.ic.operation.model.dao.pricelib.jcycljg.myzs;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.MyzsEntity;


public interface MyzsDao extends GetEntitiesDao<MyzsEntity>, AbstractReadWriteDao<MyzsEntity>{

	MyzsEntity getByDate(Date date);

}
