package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.lzbb.LzbbDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.LzbbEntity;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;
import com.util.tools.DateUtil;

@Component
public class LzbbDataStorage implements DataStorage<LzbbEntity>,
		DataStringify<LzbbEntity> {

	@Autowired
	LzbbDao dao;

	public LzbbDataStorage() {
		StorageAssemble.register(JcycljgType.LZBB, this, this);
	}

	public void store(List<Object[]> data) {
		for (Object[] objs : data) {
			LzbbEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null) {
				entity = new LzbbEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setShag((Double) objs[1]);
			entity.setNcwg((Double) objs[2]);
			entity.setSyag((Double) objs[3]);
			entity.setXaag((Double) objs[4]);
			entity.setWlmqbg((Double) objs[5]);
			entity.setScjj((Double) objs[6]);
			dao.merge(entity);
		}
	}

	@Override
	public List<List<String>> stringify(List<LzbbEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (LzbbEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(DateUtil.day(entity.getDate()));
			list.add("" + entity.getShag());
			list.add("" + entity.getNcwg());
			list.add("" + entity.getSyag());
			list.add("" + entity.getXaag());
			list.add("" + entity.getWlmqbg());
			list.add("" + entity.getScjj());
			result.add(list);
		}
		return result;
	}
}
