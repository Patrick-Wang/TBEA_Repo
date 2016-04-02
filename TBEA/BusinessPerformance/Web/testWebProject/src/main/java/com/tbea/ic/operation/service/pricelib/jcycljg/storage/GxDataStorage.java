package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.gjyy.GjyyDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.gx.GxDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GxEntity;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;

@Component
public class GxDataStorage implements DataStorage<GxEntity>,
		DataStringify<GxEntity> {

	@Autowired
	GxDao dao;

	public GxDataStorage() {
		StorageAssemble.register(JcycljgType.GX, this, this);
	}

	public void store(List<Object[]> data) {
		for (Object[] objs : data) {
			GxEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null) {
				entity = new GxEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setShsg((Double) objs[1]);
			entity.setNjjy((Double) objs[2]);
			entity.setZzjy((Double) objs[3]);
			entity.setTjtg((Double) objs[4]);
			entity.setCdjg((Double) objs[5]);
			entity.setPjj((Double) objs[6]);
			dao.merge(entity);
		}
	}

	@Override
	public List<List<String>> stringify(List<GxEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (GxEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(Util.formatToDay(entity.getDate()));
			list.add("" + entity.getShsg());
			list.add("" + entity.getNjjy());
			list.add("" + entity.getZzjy());
			list.add("" + entity.getTjtg());
			list.add("" + entity.getCdjg());
			list.add("" + entity.getPjj());
			result.add(list);
		}
		return result;
	}
}
