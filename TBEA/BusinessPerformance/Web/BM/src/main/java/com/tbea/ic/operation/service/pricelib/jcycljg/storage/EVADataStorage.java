package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.eva.EVADao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.EVAEntity;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;


@Component
public class EVADataStorage implements DataStorage<EVAEntity>,
		DataStringify<EVAEntity> {

	@Autowired
	EVADao dao;

	public EVADataStorage() {
		StorageAssemble.register(JcycljgType.EVA, this, this);
	}

	public void store(List<Object[]> data) {
		for (Object[] objs : data) {
			EVAEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null) {
				entity = new EVAEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setBjyj142((Double) objs[1]);
			entity.setBjyj183((Double) objs[2]);
			entity.setYbV5110J((Double) objs[3]);
			dao.merge(entity);
		}
	}

	@Override
	public List<List<String>> stringify(List<EVAEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (EVAEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(Util.formatToDay(entity.getDate()));
			list.add("" + entity.getBjyj142());
			list.add("" + entity.getBjyj183());
			list.add("" + entity.getYbV5110J());
			result.add(list);
		}
		return result;
	}

}
