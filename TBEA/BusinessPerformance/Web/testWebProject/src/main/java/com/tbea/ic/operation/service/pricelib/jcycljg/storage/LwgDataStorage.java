package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.util.List;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.lwg.LwgDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.LwgEntity;

public class LwgDataStorage implements DataStorage<LwgEntity> {

	public LwgDataStorage(LwgDao lwgDao) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void store(List<Object[]> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<List<String>> stringify(List<LwgEntity> objs) {
		// TODO Auto-generated method stub
		return null;
	}

}
