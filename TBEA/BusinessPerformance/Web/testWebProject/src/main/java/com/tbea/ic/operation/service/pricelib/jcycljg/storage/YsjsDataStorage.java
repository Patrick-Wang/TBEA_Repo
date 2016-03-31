package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ysjs.YsjsDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YsjsEntity;

public class YsjsDataStorage implements DataStorage{
	YsjsDao ysjsDao;
	
	public YsjsDataStorage(YsjsDao ysjsDao) {
		super();
		this.ysjsDao = ysjsDao;
	}
	
	
	public void store(List<Object[]> data){
		for (Object[] objs : data){
			YsjsEntity entity = ysjsDao.getByDate((Date) objs[0]);
			if (entity == null){
			   entity = new YsjsEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setCjxhCu((Double) objs[1]);
			entity.setCjxhAl((Double) objs[2]);
			entity.setCjxhZn((Double) objs[3]);
			entity.setLEMCu((Double) objs[4]);
			entity.setLEMAl((Double) objs[5]);
			entity.setLEMZn((Double) objs[6]);
			ysjsDao.merge(entity);
		}
	}
}
