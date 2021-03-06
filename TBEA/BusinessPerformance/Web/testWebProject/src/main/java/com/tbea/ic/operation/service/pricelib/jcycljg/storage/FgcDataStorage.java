package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.fgc.FgcDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.FgcEntity;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;
import com.util.tools.DateUtil;

@Component
public class FgcDataStorage implements DataStorage<FgcEntity>,
		DataStringify<FgcEntity> {

	@Autowired
	FgcDao dao;

	public FgcDataStorage() {
		StorageAssemble.register(JcycljgType.FGC, this, this);
	}

	public void store(List<Object[]> data) {
		for (Object[] objs : data) {
			FgcEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null) {
				entity = new FgcEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setBj((Double) objs[1]);
			entity.setTj((Double) objs[2]);
			entity.setDl((Double) objs[3]);
			entity.setTs((Double) objs[4]);
			dao.merge(entity);
		}
	}

	@Override
	public List<List<String>> stringify(List<FgcEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (FgcEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(DateUtil.day(entity.getDate()));
			list.add("" + entity.getBj());
			list.add("" + entity.getTj());
			list.add("" + entity.getDl());
			list.add("" + entity.getTs());
			result.add(list);
		}
		return result;
	}

}
