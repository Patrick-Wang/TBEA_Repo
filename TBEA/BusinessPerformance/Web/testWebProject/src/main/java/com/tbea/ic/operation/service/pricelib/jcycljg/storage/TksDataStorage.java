package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.tks.TksDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.TksEntity;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;
import com.util.tools.DateUtil;

@Component
public class TksDataStorage implements DataStorage<TksEntity>,
		DataStringify<TksEntity> {

	@Autowired
	TksDao dao;

	public TksDataStorage() {
		StorageAssemble.register(JcycljgType.TKS, this, this);
	}

	public void store(List<Object[]> data) {
		for (Object[] objs : data) {
			TksEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null) {
				entity = new TksEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setSxdx((Double) objs[1]);
			entity.setLnly((Double) objs[2]);
			entity.setSdzb((Double) objs[3]);
			entity.setAhhq((Double) objs[4]);
			entity.setQdgbxfk((Double) objs[5]);
			entity.setYdfk((Double) objs[6]);
			dao.merge(entity);
		}
	}

	@Override
	public List<List<String>> stringify(List<TksEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (TksEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(DateUtil.day(entity.getDate()));
			list.add("" + entity.getSxdx());
			list.add("" + entity.getLnly());
			list.add("" + entity.getSdzb());
			list.add("" + entity.getAhhq());
			list.add("" + entity.getQdgbxfk());
			list.add("" + entity.getYdfk());
			result.add(list);
		}
		return result;
	}

}
