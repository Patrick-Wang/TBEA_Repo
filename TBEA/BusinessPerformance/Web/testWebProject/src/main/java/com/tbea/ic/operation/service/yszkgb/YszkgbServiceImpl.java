package com.tbea.ic.operation.service.yszkgb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.yszkgb.yszkyjtztjqs.YszkYjtzTjqsDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkyjtztjqs.YszkYjtzTjqsDao;
import com.tbea.ic.operation.model.dao.yszkgb.yqyszcsys.YqyszcsysDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yqyszcsys.YqyszcsysDao;
import com.tbea.ic.operation.model.dao.yszkgb.yszkkxxz.YszkKxxzDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkkxxz.YszkKxxzDao;
import com.tbea.ic.operation.model.dao.yszkgb.yszkzl.YszkZlDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkzl.YszkZlDao;

import javax.annotation.Resource;

import com.tbea.ic.operation.model.dao.yszkgb.yszkzm.YszkzmDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkzm.YszkzmDao;
import com.tbea.ic.operation.model.entity.yszkgb.YszkZlEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkzmEntity;
import com.tbea.ic.operation.service.yszkgb.YszkgbService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(YszkgbServiceImpl.NAME)
@Transactional("transactionManager")
public class YszkgbServiceImpl implements YszkgbService {
	@Resource(name=YszkYjtzTjqsDaoImpl.NAME)
	YszkYjtzTjqsDao yszkYjtzTjqsDao;

	@Resource(name=YqyszcsysDaoImpl.NAME)
	YqyszcsysDao yqyszcsysDao;

	@Resource(name=YszkKxxzDaoImpl.NAME)
	YszkKxxzDao yszkKxxzDao;

	@Resource(name=YszkZlDaoImpl.NAME)
	YszkZlDao yszkZlDao;

	@Resource(name=YszkzmDaoImpl.NAME)
	YszkzmDao yszkzmDao;

	public final static String NAME = "YszkgbServiceImpl";

	@Override
	public List<List<String>> getZmb(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<YszkzmEntity> entities= yszkzmDao.getByDate(d, company);
		for (YszkzmEntity entity : entities){
			List<String> list = new ArrayList<String>();
			list.add("" + entity.getZmje());
			list.add("" + entity.getHzzb());
			list.add("" + entity.getYz());
			result.add(list);
		}
		return result;
	}

	private List<String> toList(YszkZlEntity entity){
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getZl5nys());
		list.add("" + entity.getZl4z5n());
		list.add("" + entity.getZl3z4n());
		list.add("" + entity.getZl2z3n());
		list.add("" + entity.getZl1z2n());
		list.add("" + entity.getZl1nyn());
		list.add("" + entity.getHj());
		return list;
	}
	
	@Override
	public List<List<String>> getYszkzlbh(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		List<YszkZlEntity> entities= yszkZlDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		for (int i = 0; i < 12; ++i){
			result.add(new ArrayList<String>());
			for (YszkZlEntity entity : entities){
				if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH)){
					result.set(result.size() - 1, toList(entity));
					entities.remove(entity);
					break;
				}
			}
			cal.add(Calendar.MONTH, 1);
		}
		
		return result;
	}

}
