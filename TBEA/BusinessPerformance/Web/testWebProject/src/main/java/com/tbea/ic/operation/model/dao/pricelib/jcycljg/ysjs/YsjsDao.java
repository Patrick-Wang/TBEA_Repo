package com.tbea.ic.operation.model.dao.pricelib.jcycljg.ysjs;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YsjsEntity;


public interface YsjsDao {

	List<YsjsEntity> getYsjs(Date start, Date end);

}
