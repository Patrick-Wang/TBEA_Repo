package com.tbea.ic.operation.service.yszkgb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
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

import net.sf.json.JSONArray;

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

	@Override
	public List<List<String>> getYszkkxxzEntry(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		YszkKxxzEntity entity= yszkKxxzDao.getByDate(d, company);
		if (null != entity){
			List<String> list = toList(entity);
			int size = list.size();
			list.remove(size - 1);//合计
			list.remove(size - 4);//小计
			result.add(list);
		}else{
			result.add(new ArrayList<>());
		}
		return result;
	}

	@Override
	public List<List<String>> getYqyszcsysEntry(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		YqyszcsysEntity entity= yqyszcsysDao.getByDate(d, company);
		if (null != entity){
			List<String> list = toList(entity);
			int size = list.size();
			list.remove(size - 1);//合计
			result.add(list);
		}else{
			result.add(new ArrayList<>());
		}
		return result;
	}

	@Override
	public List<List<String>> getYszkyjtztjqsEntry(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		YszkYjtzTjqsEntity entity= yszkYjtzTjqsDao.getByDate(d, company);
		if (null != entity){
			List<String> list = toList(entity);
			result.add(list);
		}else{
			result.add(new ArrayList<>());
		}
		return result;
	}

	
	ErrorCode entryYszkkxxz(Date d, Company company, JSONArray data, ZBStatus status) {
		data = data.getJSONArray(0);
		ErrorCode err = ErrorCode.OK;
		YszkKxxzEntity entity= yszkKxxzDao.getByDate(d, company);
		if (null == entity){
			entity = new YszkKxxzEntity();
			Calendar cal = Calendar.getInstance();
			entity.setNf(cal.get(Calendar.YEAR));
			entity.setYf(cal.get(Calendar.MONTH) + 1);
		}

		entity.setZt(status.ordinal());
		entity.setYq0z1y(Util.toDoubleNull(data.getString(0)));
		entity.setYq1z3y(Util.toDoubleNull(data.getString(1)));
		entity.setYq3z6y(Util.toDoubleNull(data.getString(2)));
		entity.setYq6z12y(Util.toDoubleNull(data.getString(3)));
		entity.setYq1nys(Util.toDoubleNull(data.getString(4)));
		entity.setWdq(Util.toDoubleNull(data.getString(5)));
		entity.setWdqzbj(Util.toDoubleNull(data.getString(6)));
		yszkKxxzDao.merge(entity);
		return err;
	}

	ErrorCode entryYqyszcsys(Date d, Company company, JSONArray data, ZBStatus status) {
		data = data.getJSONArray(0);
		ErrorCode err = ErrorCode.OK;
		YqyszcsysEntity entity= yqyszcsysDao.getByDate(d, company);
		if (null == entity){
			entity = new YqyszcsysEntity();
			Calendar cal = Calendar.getInstance();
			entity.setNf(cal.get(Calendar.YEAR));
			entity.setYf(cal.get(Calendar.MONTH) + 1);
		}

		entity.setZt(status.ordinal());
		entity.setNbys(Util.toDoubleNull(data.getString(0)));
		entity.setKhzx(Util.toDoubleNull(data.getString(1)));
		entity.setGdfk(Util.toDoubleNull(data.getString(2)));
		entity.setXmbh(Util.toDoubleNull(data.getString(3)));
		entity.setHtys(Util.toDoubleNull(data.getString(4)));
		entity.setSxbl(Util.toDoubleNull(data.getString(5)));
		entity.setSs(Util.toDoubleNull(data.getString(6)));
		yqyszcsysDao.merge(entity);
		return err;
	}

	ErrorCode entryYszkyjtztjqs(Date d, Company company, JSONArray data, ZBStatus status) {
		data = data.getJSONArray(0);
		ErrorCode err = ErrorCode.OK;
		YszkYjtzTjqsEntity entity= yszkYjtzTjqsDao.getByDate(d, company);
		if (null == entity){
			entity = new YszkYjtzTjqsEntity();
			Calendar cal = Calendar.getInstance();
			entity.setNf(cal.get(Calendar.YEAR));
			entity.setYf(cal.get(Calendar.MONTH) + 1);
		}

		entity.setZt(status.ordinal());
		entity.setCwzmysjsye(Util.toDoubleNull(data.getString(0)));
		entity.setBlye(Util.toDoubleNull(data.getString(1)));
		entity.setHfpwkje(Util.toDoubleNull(data.getString(2)));
		entity.setPkhwfje(Util.toDoubleNull(data.getString(3)));
		entity.setYskcjys(Util.toDoubleNull(data.getString(4)));
		entity.setXyzcjys(Util.toDoubleNull(data.getString(5)));
		entity.setQtyskmyx(Util.toDoubleNull(data.getString(6)));
		entity.setYjtzyszkye(Util.toDoubleNull(data.getString(7)));
		yszkYjtzTjqsDao.merge(entity);
		return err;
	}

	@Override
	public ErrorCode saveYszkkxxz(Date d, Company company, JSONArray data) {
		return entryYszkkxxz(d, company, data, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode saveYqyszcsys(Date d, Company company, JSONArray data) {
		return entryYqyszcsys(d, company, data, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode saveYszkyjtztjqs(Date d, Company company, JSONArray data) {
		return entryYszkyjtztjqs(d, company, data, ZBStatus.SAVED);
	}
	
	@Override
	public ErrorCode submitYszkkxxz(Date d, Company company, JSONArray data) {
		return entryYqyszcsys(d, company, data, ZBStatus.SUBMITTED);
	}

	@Override
	public ErrorCode submitYqyszcsys(Date d, Company company, JSONArray data) {
		return entryYqyszcsys(d, company, data, ZBStatus.SUBMITTED);
	}

	@Override
	public ErrorCode submitYszkyjtztjqs(Date d, Company company, JSONArray data) {
		return entryYszkyjtztjqs(d, company, data, ZBStatus.SUBMITTED);
	}


	@Override
	public ZBStatus getYszkkxxzStatus(Date d, Company comp) {
		YszkKxxzEntity entity= yszkKxxzDao.getByDate(d, comp);
		if (null != entity){
			return ZBStatus.valueOf(entity.getZt());
		}
		return null;
	}

	@Override
	public ZBStatus getYqyszcsysStatus(Date d, Company comp) {
		YqyszcsysEntity entity= yqyszcsysDao.getByDate(d, comp);
		if (null != entity){
			return ZBStatus.valueOf(entity.getZt());
		}
		return null;
	}

	@Override
	public ZBStatus getYszkyjtztjqsStatus(Date d, Company comp) {
		YszkYjtzTjqsEntity entity= yszkYjtzTjqsDao.getByDate(d, comp);
		if (null != entity){
			return ZBStatus.valueOf(entity.getZt());
		}
		return null;
	}

	
}
