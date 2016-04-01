package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.gjyy.GjyyDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;

public class GjyyDataStorage implements DataStorage<GjyyEntity> {

	GjyyDao dao;
	
	public GjyyDataStorage(GjyyDao dao) {
		super();
		this.dao = dao;
	}
	
	
	public void store(List<Object[]> data){
		for (Object[] objs : data){
			GjyyEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null){
			   entity = new GjyyEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setWTI((Double) objs[1]);
			entity.setBlt((Double) objs[2]);
			dao.merge(entity);
		}
	}


	@Override
	public List<List<String>> stringify(List<GjyyEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (GjyyEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(Util.formatToDay(entity.getDate()));
			list.add("" + entity.getWTI());
			list.add("" + entity.getBlt());
			result.add(list);
		}
		return result;
	}

}
