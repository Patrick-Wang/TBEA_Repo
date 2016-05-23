package com.tbea.ic.operation.model.dao.pricelib.jcycljg.pmicpippi;

import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.PmiCpiPpiEntity;


public interface PmiCpiPpiDao extends GetEntitiesDao<PmiCpiPpiEntity>, AbstractReadWriteDao<PmiCpiPpiEntity> {

	PmiCpiPpiEntity getByDate(Date date);

}
