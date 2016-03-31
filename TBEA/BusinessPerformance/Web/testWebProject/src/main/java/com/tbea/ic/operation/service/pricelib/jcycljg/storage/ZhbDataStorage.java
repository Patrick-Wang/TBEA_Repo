package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.util.List;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.zhb.ZhbDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.ZhbEntity;

public class ZhbDataStorage implements DataStorage<ZhbEntity> {

	public ZhbDataStorage(ZhbDao zhbDao) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void store(List<Object[]> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<List<String>> stringify(List<ZhbEntity> objs) {
		// TODO Auto-generated method stub
		return null;
	}

}
