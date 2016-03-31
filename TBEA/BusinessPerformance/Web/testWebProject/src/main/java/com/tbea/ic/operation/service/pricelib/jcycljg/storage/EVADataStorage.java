package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.util.List;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.eva.EVADao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.EVAEntity;

public class EVADataStorage implements DataStorage<EVAEntity> {

	public EVADataStorage(EVADao evaDao) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void store(List<Object[]> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<List<String>> stringify(List<EVAEntity> objs) {
		// TODO Auto-generated method stub
		return null;
	}

}
