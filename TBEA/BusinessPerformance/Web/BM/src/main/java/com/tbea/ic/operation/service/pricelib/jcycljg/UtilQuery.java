package com.tbea.ic.operation.service.pricelib.jcycljg;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.GetEntitiesDao;

class UtilQuery {
	GetEntitiesDao<?> dao;
	public UtilQuery(GetEntitiesDao<?> dao){
		this.dao = dao;
	}
	
	public List<?> query(Date start, Date end) {
		return dao.getEntities(start, end);
	}
}