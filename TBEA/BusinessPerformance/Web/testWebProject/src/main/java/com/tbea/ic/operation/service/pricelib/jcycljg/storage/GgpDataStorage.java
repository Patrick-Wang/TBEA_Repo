package com.tbea.ic.operation.service.pricelib.jcycljg.storage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.model.dao.pricelib.jcycljg.ggp.GgpDao;
import com.tbea.ic.operation.model.entity.pricelib.jcycljg.GgpEntity;

public class GgpDataStorage implements DataStorage<GgpEntity> {

	GgpDao ggpDao;
	public GgpDataStorage(GgpDao ggpDao) {
		this.ggpDao = ggpDao;
	}

	@Override
	public void store(List<Object[]> data) {
		for (Object[] objs : data){
			GgpEntity entity = ggpDao.getByDate((Date) objs[0]);
			if (entity == null){
			   entity = new GgpEntity();
			}
			entity.setDate((Date) objs[0]);
			entity.setWg30q120((Double) objs[1]);
			entity.setWg30pk100((Double) objs[2]);
			entity.setWg27pk095((Double) objs[3]);
			entity.setWg23pk085((Double) objs[4]);
			entity.setBgb30p120((Double) objs[5]);
			entity.setBgb30p110((Double) objs[6]);
			entity.setBgb27r095((Double) objs[7]);
			entity.setBgb27r085((Double) objs[8]);
			ggpDao.merge(entity);
		}
	}

	@Override
	public List<List<String>> stringify(List<GgpEntity> entitys) {
		List<List<String>> result = new ArrayList<List<String>>();
		for (GgpEntity entity : entitys) {
			List<String> list = new ArrayList<String>();
			list.add(Util.formatToMonth(entity.getDate()));
			list.add("" + entity.getWg30q120());
			list.add("" + entity.getWg30pk100());
			list.add("" + entity.getWg27pk095());
			list.add("" + entity.getWg23pk085());
			list.add("" + entity.getBgb30p120());
			list.add("" + entity.getBgb30p110());
			list.add("" + entity.getBgb27r095());
			list.add("" + entity.getBgb27r085());
			result.add(list);
		}
		return result;
	}

}
