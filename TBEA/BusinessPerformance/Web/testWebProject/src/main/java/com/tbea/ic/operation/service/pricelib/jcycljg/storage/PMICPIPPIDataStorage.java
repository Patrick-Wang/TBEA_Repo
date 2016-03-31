package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.util.List;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.pmicpippi.PmiCpiPpiDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.PmiCpiPpiEntity;

public class PMICPIPPIDataStorage implements DataStorage<PmiCpiPpiEntity> {

	public PMICPIPPIDataStorage(PmiCpiPpiDao pmiCpiPpiDao) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void store(List<Object[]> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<List<String>> stringify(List<PmiCpiPpiEntity> objs) {
		// TODO Auto-generated method stub
		return null;
	}

}
