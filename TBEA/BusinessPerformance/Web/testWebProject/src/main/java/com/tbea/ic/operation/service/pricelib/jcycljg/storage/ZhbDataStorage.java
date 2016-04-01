package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.zhb.ZhbDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.ZhbEntity;

public class ZhbDataStorage implements DataStorage<ZhbEntity> {

	ZhbDao dao;
	
	public ZhbDataStorage(ZhbDao dao) {
		super();
		this.dao = dao;
	}
	
	
	public void store(List<Object[]> data){
		for (Object[] objs : data){
			ZhbEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null){
			   entity = new ZhbEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setShmg((Double) objs[1]);
			entity.setNjmg((Double) objs[2]);
			entity.setGzsg((Double) objs[3]);
			entity.setCspg((Double) objs[4]);
			entity.setBjlg((Double) objs[5]);
			entity.setSytg((Double) objs[6]);
			entity.setWlmqbg((Double) objs[7]);
			entity.setPjj((Double) objs[8]);
			dao.merge(entity);
		}
	}


	@Override
	public List<List<String>> stringify(List<ZhbEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (ZhbEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(Util.formatToDay(entity.getDate()));
			list.add("" + entity.getShmg());
			list.add("" + entity.getNjmg());
			list.add("" + entity.getGzsg());
			list.add("" + entity.getCspg());
			list.add("" + entity.getBjlg());
			list.add("" + entity.getSytg());
			list.add("" + entity.getWlmqbg());
			list.add("" + entity.getPjj());
			result.add(list);
		}
		return result;
	}

}
