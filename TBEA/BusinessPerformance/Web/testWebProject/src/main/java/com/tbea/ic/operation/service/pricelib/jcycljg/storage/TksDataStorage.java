package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.util.List;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.tks.TksDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.TksEntity;

public class TksDataStorage implements DataStorage<TksEntity> {

	public TksDataStorage(TksDao tksDao) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void store(List<Object[]> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<List<String>> stringify(List<TksEntity> objs) {
		// TODO Auto-generated method stub
		return null;
	}

}
