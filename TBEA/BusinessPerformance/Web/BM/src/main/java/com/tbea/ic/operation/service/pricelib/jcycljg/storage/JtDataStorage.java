package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.jkzj.JkzjDao;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.jt.JtDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.JkzjEntity;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.JtEntity;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;

@Component
public class JtDataStorage implements DataStorage<JtEntity>,
		DataStringify<JtEntity> {

	@Autowired
	JtDao dao;

	public JtDataStorage() {
		StorageAssemble.register(JcycljgType.JT, this, this);
	}

	public void store(List<Object[]> data) {
		for (Object[] objs : data) {
			JtEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null) {
				entity = new JtEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setSxll((Double) objs[1]);
			entity.setHbxt((Double) objs[2]);
			entity.setSdqd((Double) objs[3]);
			entity.setHnjy((Double) objs[4]);
			dao.merge(entity);
		}
	}

	@Override
	public List<List<String>> stringify(List<JtEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (JtEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(Util.formatToDay(entity.getDate()));
			list.add("" + entity.getSxll());
			list.add("" + entity.getHbxt());
			list.add("" + entity.getSdqd());
			list.add("" + entity.getHnjy());
			result.add(list);
		}
		return result;
	}

}
