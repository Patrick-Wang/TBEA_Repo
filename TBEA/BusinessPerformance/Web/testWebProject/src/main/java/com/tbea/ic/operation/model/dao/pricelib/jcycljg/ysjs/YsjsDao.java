package com.tbea.ic.operation.model.dao.pricelib.jcycljg.ysjs;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YsjsEntity;


public interface YsjsDao  extends AbstractReadWriteDao<YsjsEntity>{

	List<YsjsEntity> getYsjs(Date start, Date end);

	YsjsEntity getByDate(Date date);

}
