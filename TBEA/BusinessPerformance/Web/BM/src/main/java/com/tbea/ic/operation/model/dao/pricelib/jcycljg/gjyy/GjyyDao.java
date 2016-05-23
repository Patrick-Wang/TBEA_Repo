package com.tbea.ic.operation.model.dao.pricelib.jcycljg.gjyy;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GgpEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;


public interface GjyyDao   extends GetEntitiesDao<GjyyEntity>, AbstractReadWriteDao<GjyyEntity>{

	GjyyEntity getByDate(Date date);

}
