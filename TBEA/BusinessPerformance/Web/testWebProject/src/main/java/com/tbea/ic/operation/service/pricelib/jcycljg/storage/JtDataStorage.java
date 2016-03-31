package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.util.List;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.jt.JtDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.JtEntity;

public class JtDataStorage implements DataStorage<JtEntity> {

	public JtDataStorage(JtDao jtDao) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void store(List<Object[]> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<List<String>> stringify(List<JtEntity> objs) {
		// TODO Auto-generated method stub
		return null;
	}

}
