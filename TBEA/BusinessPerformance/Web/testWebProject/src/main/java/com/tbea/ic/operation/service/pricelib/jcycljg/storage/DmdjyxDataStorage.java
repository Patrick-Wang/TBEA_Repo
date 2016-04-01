package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.dmdjyx.DmdjyxDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.DmdjyxEntity;

public class DmdjyxDataStorage implements DataStorage<DmdjyxEntity> {

	DmdjyxDao dao;
	public DmdjyxDataStorage(DmdjyxDao dao) {
		this.dao = dao;
	}

	public void store(List<Object[]> data){
		for (Object[] objs : data){
			DmdjyxEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null){
			   entity = new DmdjyxEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setShsh((Double) objs[1]);
			entity.setYssh((Double) objs[2]);
			dao.merge(entity);
		}
	}


	@Override
	public List<List<String>> stringify(List<DmdjyxEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (DmdjyxEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(Util.formatToDay(entity.getDate()));
			list.add("" + entity.getShsh());
			list.add("" + entity.getYssh());
			result.add(list);
		}
		return result;
	}

}
