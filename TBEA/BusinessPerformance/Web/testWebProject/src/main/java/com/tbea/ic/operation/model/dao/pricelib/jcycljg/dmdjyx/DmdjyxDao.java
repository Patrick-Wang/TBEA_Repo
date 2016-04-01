package com.tbea.ic.operation.model.dao.pricelib.jcycljg.dmdjyx;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.DmdjyxEntity;


public interface DmdjyxDao extends GetEntitiesDao<DmdjyxEntity>, AbstractReadWriteDao<DmdjyxEntity>{

	DmdjyxEntity getByDate(Date date);

}
