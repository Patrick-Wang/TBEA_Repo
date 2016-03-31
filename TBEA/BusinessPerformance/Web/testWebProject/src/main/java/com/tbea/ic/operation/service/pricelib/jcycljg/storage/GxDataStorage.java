package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.util.List;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.gx.GxDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GxEntity;

public class GxDataStorage implements DataStorage<GxEntity> {

	public GxDataStorage(GxDao gxDao) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void store(List<Object[]> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<List<String>> stringify(List<GxEntity> objs) {
		// TODO Auto-generated method stub
		return null;
	}

}
