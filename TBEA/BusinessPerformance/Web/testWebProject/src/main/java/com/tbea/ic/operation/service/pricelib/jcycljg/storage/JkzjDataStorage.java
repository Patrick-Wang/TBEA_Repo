package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.jkzj.JkzjDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.JkzjEntity;

public class JkzjDataStorage implements DataStorage<JkzjEntity> {


	JkzjDao dao;
	
	public JkzjDataStorage(JkzjDao dao) {
		super();
		this.dao = dao;
	}
	
	
	public void store(List<Object[]> data){
		for (Object[] objs : data){
			Date dt = (Date) objs[0];
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			objs[0] = new Date(cal.getTimeInMillis());
			JkzjEntity entity = dao.getByDate((Date) objs[0]);
			if (entity == null){
			   entity = new JkzjEntity();
			}
		
			entity.setDate((Date) objs[0]);
			entity.setJndqzwz((Double) objs[1]);
			entity.setJndgy((Double) objs[2]);
			entity.setZljx((Double) objs[3]);
			dao.merge(entity);
		}
	}


	@Override
	public List<List<String>> stringify(List<JkzjEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (JkzjEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(Util.formatToMonth(entity.getDate()));
			list.add("" + entity.getJndqzwz());
			list.add("" + entity.getJndgy());
			list.add("" + entity.getZljx());
			result.add(list);
		}
		return result;
	}

}
