package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ysjs.YsjsDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YsjsEntity;

public class YsjsDataStorage implements DataStorage<YsjsEntity>{
	YsjsDao dao;
	
	public YsjsDataStorage(YsjsDao dao) {
		super();
		this.dao = dao;
	}
	
	
	public void store(List<Object[]> data){
		for (Object[] objs : data){
			YsjsEntity entity = dao.getByDate((Date) objs[0]);
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
			dao.merge(entity);
		}
	}


	@Override
	public List<List<String>> stringify(List<YsjsEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (YsjsEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(Util.formatToDay(entity.getDate()));
			list.add("" + entity.getCjxhCu());
			list.add("" + entity.getCjxhAl());
			list.add("" + entity.getCjxhZn());
			list.add("" + entity.getLEMCu());
			list.add("" + entity.getLEMAl());
			list.add("" + entity.getLEMZn());
			result.add(list);
		}
		return result;
	}
}
