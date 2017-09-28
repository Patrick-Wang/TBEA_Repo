package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.gjyy.GjyyDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GjyyEntity;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;
import com.util.tools.DateUtil;

@Component
public class GjyyDataStorage implements DataStorage<GjyyEntity>,
		DataStringify<GjyyEntity> {

	@Autowired
	GjyyDao dao;

	public GjyyDataStorage() {
		StorageAssemble.register(JcycljgType.GJYY, this, this);
	}

	public void store(List<Object[]> data) {
		for (Object[] objs : data) {
			GjyyEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null) {
				entity = new GjyyEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setWTI((Double) objs[1]);
			entity.setBlt((Double) objs[2]);
			dao.merge(entity);
		}
	}

	@Override
	public List<List<String>> stringify(List<GjyyEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (GjyyEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(DateUtil.day(entity.getDate()));
			list.add("" + entity.getWTI());
			list.add("" + entity.getBlt());
			result.add(list);
		}
		return result;
	}

}
