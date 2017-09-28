package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.model.dao.pricelib.jcycljg.pmicpippi.PmiCpiPpiDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.PmiCpiPpiEntity;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;
import com.util.tools.DateUtil;

@Component
public class PMICPIPPIDataStorage implements DataStorage<PmiCpiPpiEntity>,
		DataStringify<PmiCpiPpiEntity> {

	@Autowired
	PmiCpiPpiDao dao;

	public PMICPIPPIDataStorage() {
		StorageAssemble.register(JcycljgType.PMICPIPPI, this, this);
	}

	public void store(List<Object[]> data) {
		for (Object[] objs : data) {
			Date dt = (Date) objs[0];
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			objs[0] = new Date(cal.getTimeInMillis());
			PmiCpiPpiEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null) {
				entity = new PmiCpiPpiEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setPmi((Double) objs[1]);
			entity.setCpi((Double) objs[2]);
			entity.setPpi((Double) objs[3]);
			dao.merge(entity);
		}
	}

	@Override
	public List<List<String>> stringify(List<PmiCpiPpiEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (PmiCpiPpiEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(DateUtil.month(entity.getDate()));
			list.add("" + entity.getPmi());
			list.add("" + entity.getCpi());
			list.add("" + entity.getPpi());
			result.add(list);
		}
		return result;
	}

}
