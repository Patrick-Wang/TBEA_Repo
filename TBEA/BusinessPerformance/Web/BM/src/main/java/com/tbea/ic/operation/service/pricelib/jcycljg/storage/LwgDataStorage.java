package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.jt.JtDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.lwg.LwgDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.JtEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.LwgEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.ZhbEntity;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;

@Component
public class LwgDataStorage implements DataStorage<LwgEntity>,
		DataStringify<LwgEntity> {

	@Autowired
	LwgDao dao;

	public LwgDataStorage() {
		StorageAssemble.register(JcycljgType.LWG, this, this);
	}

	public void store(List<Object[]> data) {
		for (Object[] objs : data) {
			LwgEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null) {
				entity = new LwgEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setSh1214mm((Double) objs[1]);
			entity.setHz1214mm((Double) objs[2]);
			entity.setNj1214mm((Double) objs[3]);
			entity.setTj1214mm((Double) objs[4]);
			entity.setSh1625mm((Double) objs[5]);
			entity.setHz1625mm((Double) objs[6]);
			entity.setNj1625mm((Double) objs[7]);
			entity.setTj1625mm((Double) objs[8]);
			dao.merge(entity);
		}
	}

	@Override
	public List<List<String>> stringify(List<LwgEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (LwgEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(Util.formatToDay(entity.getDate()));
			list.add("" + entity.getSh1214mm());
			list.add("" + entity.getHz1214mm());
			list.add("" + entity.getNj1214mm());
			list.add("" + entity.getTj1214mm());
			list.add("" + entity.getSh1625mm());
			list.add("" + entity.getHz1625mm());
			list.add("" + entity.getNj1625mm());
			list.add("" + entity.getTj1625mm());
			result.add(list);
		}
		return result;
	}
}
