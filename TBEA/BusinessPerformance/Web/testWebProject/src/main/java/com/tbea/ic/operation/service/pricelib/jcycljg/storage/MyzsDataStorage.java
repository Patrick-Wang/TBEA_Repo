package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.util.List;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.myzs.MyzsDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.MyzsEntity;

public class MyzsDataStorage implements DataStorage<MyzsEntity> {

	public MyzsDataStorage(MyzsDao myzsDao) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void store(List<Object[]> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<List<String>> stringify(List<MyzsEntity> objs) {
		// TODO Auto-generated method stub
		return null;
	}

}
