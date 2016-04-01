package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.yhjzll.YhjzllDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ysjs.YsjsDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YhjzllEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YsjsEntity;

public class YhjzllDataStorage implements DataStorage<YhjzllEntity> {


	YhjzllDao dao;
	
	public YhjzllDataStorage(YhjzllDao dao) {
		super();
		this.dao = dao;
	}
	
	
	public void store(List<Object[]> data){
		for (Object[] objs : data){
			YhjzllEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null){
			   entity = new YhjzllEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setDk6gyn((Double) objs[1]);
			entity.setDk6gyz1n((Double) objs[2]);
			entity.setDk1z3n((Double) objs[3]);
			entity.setCkhq((Double) objs[4]);
			entity.setCkdqbn((Double) objs[5]);
			entity.setCkdqyn((Double) objs[6]);
			dao.merge(entity);
		}
	}


	@Override
	public List<List<String>> stringify(List<YhjzllEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (YhjzllEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(Util.formatToDay(entity.getDate()));
			list.add("" + entity.getDk6gyn());
			list.add("" + entity.getDk6gyz1n());
			list.add("" + entity.getDk1z3n());
			list.add("" + entity.getCkhq());
			list.add("" + entity.getCkdqbn());
			list.add("" + entity.getCkdqyn());
			result.add(list);
		}
		return result;
	}
}
