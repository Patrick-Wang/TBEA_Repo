package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.myzs.MyzsDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.MyzsEntity;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;

@Component
public class MyzsDataStorage implements DataStorage<MyzsEntity>,
		DataStringify<MyzsEntity> {

	@Autowired
	MyzsDao dao;

	public MyzsDataStorage() {
		StorageAssemble.register(JcycljgType.MYZS, this, this);
	}

	public void store(List<Object[]> data) {
		for (Object[] objs : data) {
			MyzsEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null) {
				entity = new MyzsEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setMyzs((Double) objs[1]);
			dao.merge(entity);
		}
	}

	@Override
	public List<List<String>> stringify(List<MyzsEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (MyzsEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(Util.formatToDay(entity.getDate()));
			list.add("" + entity.getMyzs());
			result.add(list);
		}
		return result;
	}

}
