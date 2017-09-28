package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.pvcsz.PVCSzDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.PVCSzEntity;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;
import com.util.tools.DateUtil;

@Component
public class PVCSzDataStorage implements DataStorage<PVCSzEntity>,
		DataStringify<PVCSzEntity> {

	@Autowired
	PVCSzDao dao;

	public PVCSzDataStorage() {
		StorageAssemble.register(JcycljgType.PVCSZ, this, this);
	}

	public void store(List<Object[]> data) {
		for (Object[] objs : data) {
			PVCSzEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null) {
				entity = new PVCSzEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setTzyh((Double) objs[1]);
			entity.setHnzh((Double) objs[2]);
			entity.setSxys((Double) objs[3]);
			entity.setHljhhhg((Double) objs[4]);
			entity.setHnyh((Double) objs[5]);
			entity.setSxbyhg((Double) objs[6]);
			entity.setYbty((Double) objs[7]);
			entity.setTjdhh((Double) objs[8]);
			dao.merge(entity);
		}
	}

	@Override
	public List<List<String>> stringify(List<PVCSzEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (PVCSzEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(DateUtil.day(entity.getDate()));
			list.add("" + entity.getTzyh());
			list.add("" + entity.getHnzh());
			list.add("" + entity.getSxys());
			list.add("" + entity.getHljhhhg());
			list.add("" + entity.getHnyh());
			list.add("" + entity.getSxbyhg());
			list.add("" + entity.getYbty());
			list.add("" + entity.getTjdhh());
			result.add(list);
		}
		return result;
	}

}
