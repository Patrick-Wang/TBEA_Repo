package com.tbea.ic.operation.service.yszkgb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.Util;
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
import com.tbea.ic.operation.model.entity.yszkgb.YqyszcsysEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkKxxzEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkYjtzTjqsEntity;
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
				if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == (cal.get(Calendar.MONTH) + 1)){
					result.set(result.size() - 1, toList(entity));
					entities.remove(entity);
					break;
				}
			}
			if (result.get(result.size() - 1).isEmpty()){
				Util.resize(result.get(result.size() - 1), 7);
			}
			cal.add(Calendar.MONTH, 1);
		}
		
		return result;
	}

	@Override
	public List<List<String>> getYszkkxxz(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		List<YszkKxxzEntity> entities= yszkKxxzDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		for (int i = 0; i < 12; ++i){
			result.add(new ArrayList<String>());
			for (YszkKxxzEntity entity : entities){
				if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == (cal.get(Calendar.MONTH) + 1)){
					result.set(result.size() - 1, toList(entity));
					entities.remove(entity);
					break;
				}
			}
			if (result.get(result.size() - 1).isEmpty()){
				Util.resize(result.get(result.size() - 1), 9);
			}
			cal.add(Calendar.MONTH, 1);
		}
		
		return result;
	}

	private List<String> toList(YszkKxxzEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getYq0z1y());
		list.add("" + entity.getYq1z3y());
		list.add("" + entity.getYq3z6y());
		list.add("" + entity.getYq6z12y());
		list.add("" + entity.getYq1nys());
		list.add("" + Util.sum(new Double[]{
				entity.getYq0z1y(),
				entity.getYq1z3y(),
				entity.getYq3z6y(),
				entity.getYq1nys()}));
		list.add("" + entity.getWdq());
		list.add("" + entity.getWdqzbj());
		list.add("" + Util.sum(new Double[]{
				entity.getYq0z1y(),
				entity.getYq1z3y(),
				entity.getYq3z6y(),
				entity.getYq1nys(),
				entity.getWdq(),
				entity.getWdqzbj()}));
		return list;
	}

	@Override
	public List<List<String>> getYszkyjtztjqs(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		List<YszkYjtzTjqsEntity> entities= yszkYjtzTjqsDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		for (int i = 0; i < 12; ++i){
			result.add(new ArrayList<String>());
			for (YszkYjtzTjqsEntity entity : entities){
				if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == (cal.get(Calendar.MONTH) + 1)){
					result.set(result.size() - 1, toList(entity));
					entities.remove(entity);
					break;
				}
			}
			if (result.get(result.size() - 1).isEmpty()){
				Util.resize(result.get(result.size() - 1), 8);
			}
			cal.add(Calendar.MONTH, 1);
		}
		
		return result;
	}

	private List<String> toList(YszkYjtzTjqsEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getCwzmysjsye());
		list.add("" + entity.getBlye());
		list.add("" + entity.getHfpwkje());
		list.add("" + entity.getPkhwfje());
		list.add("" + entity.getYskcjys());
		list.add("" + entity.getXyzcjys());
		list.add("" + entity.getQtyskmyx());
		list.add("" + entity.getYjtzyszkye());
		return list;
	}

	@Override
	public List<List<String>> getYqyszcsys(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		List<YqyszcsysEntity> entities= yqyszcsysDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		for (int i = 0; i < 12; ++i){
			result.add(new ArrayList<String>());
			for (YqyszcsysEntity entity : entities){
				if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == (cal.get(Calendar.MONTH) + 1)){
					result.set(result.size() - 1, toList(entity));
					entities.remove(entity);
					break;
				}
			}
			if (result.get(result.size() - 1).isEmpty()){
				Util.resize(result.get(result.size() - 1), 8);
			}
			cal.add(Calendar.MONTH, 1);
		}
		
		return result;
	}

	private List<String> toList(YqyszcsysEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getNbys());
		list.add("" + entity.getKhzx());
		list.add("" + entity.getGdfk());
		list.add("" + entity.getXmbh());
		list.add("" + entity.getHtys());
		list.add("" + entity.getSxbl());
		list.add("" + entity.getSs());
		list.add("" + Util.sum(new Double[]{
				entity.getNbys(),
				entity.getKhzx(),
				entity.getGdfk(),
				entity.getXmbh(),
				entity.getHtys(),
				entity.getSxbl(),
				entity.getSs()}));
		return list;
	}

}
