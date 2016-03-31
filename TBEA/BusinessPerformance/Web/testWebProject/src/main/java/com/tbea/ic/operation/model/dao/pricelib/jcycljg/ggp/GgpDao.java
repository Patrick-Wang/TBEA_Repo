package com.tbea.ic.operation.model.dao.pricelib.jcycljg.ggp;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.DmdjyxEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GgpEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YsjsEntity;


public interface GgpDao  extends GetEntitiesDao, AbstractReadWriteDao<GgpEntity>{

	List<GgpEntity> getEntities(Date start, Date end);

	GgpEntity getByDate(Date date);

}
