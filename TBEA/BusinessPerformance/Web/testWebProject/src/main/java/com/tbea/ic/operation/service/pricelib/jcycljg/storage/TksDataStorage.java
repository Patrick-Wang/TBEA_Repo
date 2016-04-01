package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.tks.TksDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ysjs.YsjsDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.TksEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.YsjsEntity;

public class TksDataStorage implements DataStorage<TksEntity> {


	TksDao dao;
	
	public TksDataStorage(TksDao dao) {
		super();
		this.dao = dao;
	}
	
	
	public void store(List<Object[]> data){
		for (Object[] objs : data){
			TksEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null){
			   entity = new TksEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setSxdx((Double) objs[1]);
			entity.setLnly((Double) objs[2]);
			entity.setSdzb((Double) objs[3]);
			entity.setAhhq((Double) objs[4]);
			entity.setQdgbxfk((Double) objs[5]);
			entity.setYdfk((Double) objs[6]);
			dao.merge(entity);
		}
	}


	@Override
	public List<List<String>> stringify(List<TksEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (TksEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(Util.formatToDay(entity.getDate()));
			list.add("" + entity.getSxdx());
			list.add("" + entity.getLnly());
			list.add("" + entity.getSdzb());
			list.add("" + entity.getAhhq());
			list.add("" + entity.getQdgbxfk());
			list.add("" + entity.getYdfk());
			result.add(list);
		}
		return result;
	}

}
