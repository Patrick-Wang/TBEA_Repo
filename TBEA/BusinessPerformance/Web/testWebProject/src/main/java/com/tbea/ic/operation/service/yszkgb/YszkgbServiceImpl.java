package com.tbea.ic.operation.service.yszkgb;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.CompanyNCCode;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.BMDepartmentDB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.yszkgb.yqyszcsys.YqyszcsysDao;
import com.tbea.ic.operation.model.dao.yszkgb.yqyszcsys.YqyszcsysDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkkxxz.YszkKxxzDao;
import com.tbea.ic.operation.model.dao.yszkgb.yszkkxxz.YszkKxxzDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkyjtztjqs.YszkYjtzTjqsDao;
import com.tbea.ic.operation.model.dao.yszkgb.yszkyjtztjqs.YszkYjtzTjqsDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkzl.YszkZlDao;
import com.tbea.ic.operation.model.dao.yszkgb.yszkzl.YszkZlDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkzm.YszkzmDao;
import com.tbea.ic.operation.model.dao.yszkgb.yszkzm.YszkzmDaoImpl;
import com.tbea.ic.operation.model.entity.yszkgb.YqyszcsysEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkKxxzEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkYjtzTjqsEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkZlEntity;
import com.tbea.ic.operation.model.entity.yszkgb.YszkzmEntity;
import com.tbea.ic.operation.service.util.nc.NCConnection;
import com.util.tools.DateUtil;
import com.util.tools.ListUtil;
import com.util.tools.MathUtil;

import net.sf.json.JSONArray;

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

	@Autowired
	DWXXDao dwxxDao;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	

	
	public final static String NAME = "YszkgbServiceImpl";

	@Override
	public List<List<String>> getZmb(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		YszkzmEntity entity = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entity = yszkzmDao.getByDate(d, company);
		}else{
			entity = yszkzmDao.getSumByDate(d, company.getSubCompanies());
		}
		List<String> list = new ArrayList<String>();
		if (null != entity){
			list.add("" + entity.getYz());//原值存的账面净额
			list.add("" + entity.getHzzb());
			list.add("" + entity.getZmje());//账面净额存的是原值
			
		}else{
			ListUtil.resize(list, 3);
		}
		result.add(list);
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
	public List<List<String>> getYszkzlbhDy(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		
		List<YszkZlEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = yszkZlDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		}else{
			entities = yszkZlDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies());
		}
		
		result.add(new ArrayList<String>());
		for (YszkZlEntity entity : entities){
			if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == (cal.get(Calendar.MONTH) + 1)){
				result.set(result.size() - 1, toList(entity));
				entities.remove(entity);
				break;
			}
		}
		if (result.get(result.size() - 1).isEmpty()){
			ListUtil.resize(result.get(result.size() - 1), 7);
		}
		
		return result;
	}
	
	@Override
	public List<List<String>> getYszkzlbh(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		
		List<YszkZlEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = yszkZlDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		}else{
			entities = yszkZlDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies());
		}
		
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
				ListUtil.resize(result.get(result.size() - 1), 7);
			}
			cal.add(Calendar.MONTH, 1);
		}
		
		return result;
	}

	@Override
	public List<String> getDashboardYszkzlbh(Date d) {
		List<String> result = ListUtil.resize(new ArrayList<String>(), 7);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		
		List<YszkZlEntity> entities = null;
		
		entities = yszkZlDao.getSumByDate(new Date(cal.getTimeInMillis()), d, BMDepartmentDB.getMainlyJydw(companyManager));
		
		cal.setTime(d);
		
		for (int i = 12; i > 0; --i){
			for (YszkZlEntity entity : entities){
				if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == (cal.get(Calendar.MONTH) + 1)){
					return  toList(entity);
				}
			}
			cal.add(Calendar.MONTH, -1);
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
		List<YszkKxxzEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = yszkKxxzDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		}else{
			entities = yszkKxxzDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies());
		}
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
				ListUtil.resize(result.get(result.size() - 1), 9);
			}
			cal.add(Calendar.MONTH, 1);
		}
		
		return result;
	}

	@Override
	public List<List<String>> getYszkkxxzDy(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<YszkKxxzEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = yszkKxxzDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		}else{
			entities = yszkKxxzDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies());
		}
		
		result.add(new ArrayList<String>());
		for (YszkKxxzEntity entity : entities){
			if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == (cal.get(Calendar.MONTH) + 1)){
				result.set(result.size() - 1, toList(entity));
				entities.remove(entity);
				break;
			}
		}
		if (result.get(result.size() - 1).isEmpty()){
			ListUtil.resize(result.get(result.size() - 1), 9);
		}
		
		return result;
	}
	
	@Override
	public List<String> getDashboardYszkkxxz(Date d) {
		List<String> result = ListUtil.resize(new ArrayList<String>(), 9);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		List<YszkKxxzEntity> entities = null;
		entities = yszkKxxzDao.getSumByDate(new Date(cal.getTimeInMillis()), d, BMDepartmentDB.getMainlyJydw(companyManager));
		cal.setTime(d);
		for (int i = 12; i > 0; --i){
			for (YszkKxxzEntity entity : entities){
				if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == (cal.get(Calendar.MONTH) + 1)){
					return  toList(entity);
				}
			}
			cal.add(Calendar.MONTH, -1);
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
				entity.getYq6z12y(),
				entity.getYq1nys()}));
		list.add("" + entity.getWdq());
		list.add("" + entity.getWdqzbj());
		list.add("" + Util.sum(new Double[]{
				entity.getYq0z1y(),
				entity.getYq1z3y(),
				entity.getYq3z6y(),
				entity.getYq6z12y(),
				entity.getYq1nys(),
				entity.getWdq(),
				entity.getWdqzbj()}));
		return list;
	}
	
	private List<String> toEntryList(YszkKxxzEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getYq0z1y());
		list.add("" + entity.getYq1z3y());
		list.add("" + entity.getYq3z6y());
		list.add("" + entity.getYq6z12y());
		list.add("" + entity.getYq1nys());		
		list.add("" + entity.getWdq());
		list.add("" + entity.getWdqzbj());
		return list;
	}

	@Override
	public List<List<String>> getYszkyjtztjqs(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		List<YszkYjtzTjqsEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = yszkYjtzTjqsDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		}else{
			entities = yszkYjtzTjqsDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies());
		}
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
				ListUtil.resize(result.get(result.size() - 1), 8);
			}
			cal.add(Calendar.MONTH, 1);
		}
		
		return result;
	}
	
	@Override
	public List<List<String>> getYszkyjtztjqsDy(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<YszkYjtzTjqsEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = yszkYjtzTjqsDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		}else{
			entities = yszkYjtzTjqsDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies());
		}
		
		result.add(new ArrayList<String>());
		for (YszkYjtzTjqsEntity entity : entities){
			if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == (cal.get(Calendar.MONTH) + 1)){
				result.set(result.size() - 1, toList(entity));
				entities.remove(entity);
				break;
			}
		}
		if (result.get(result.size() - 1).isEmpty()){
			ListUtil.resize(result.get(result.size() - 1), 8);
		}
		
		return result;
	}
	
	@Override
	public List<List<String>> getDashboardYszkyjtztjqs(Date d) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		List<YszkYjtzTjqsEntity> entities = null;
		entities = yszkYjtzTjqsDao.getSumByDate(new Date(cal.getTimeInMillis()), d, BMDepartmentDB.getMainlyJydw(companyManager));
		
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
				ListUtil.resize(result.get(result.size() - 1), 8);
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
	public List<List<String>> getDashboardYqyszcsys(Date d) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		List<YqyszcsysEntity> entities = null;

		entities = yqyszcsysDao.getSumByDate(new Date(cal.getTimeInMillis()), d, BMDepartmentDB.getMainlyJydw(companyManager));

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
				ListUtil.resize(result.get(result.size() - 1), 8);
			}
			cal.add(Calendar.MONTH, 1);
		}
		
		return result;
	}
	
	@Override
	public List<List<String>> getYqyszcsys(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		List<YqyszcsysEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = yqyszcsysDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		}else{
			entities = yqyszcsysDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies());
		}
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
				ListUtil.resize(result.get(result.size() - 1), 8);
			}
			cal.add(Calendar.MONTH, 1);
		}
		
		return result;
	}
	
	@Override
	public List<List<String>> getYqyszcsysDy(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<YqyszcsysEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = yqyszcsysDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		}else{
			entities = yqyszcsysDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies());
		}

		result.add(new ArrayList<String>());
		for (YqyszcsysEntity entity : entities){
			if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == (cal.get(Calendar.MONTH) + 1)){
				result.set(result.size() - 1, toList(entity));
				entities.remove(entity);
				break;
			}
		}
		if (result.get(result.size() - 1).isEmpty()){
			ListUtil.resize(result.get(result.size() - 1), 8);
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
			List<String> list = toEntryList(entity);
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
			cal.setTime(d);
			entity.setNf(cal.get(Calendar.YEAR));
			entity.setYf(cal.get(Calendar.MONTH) + 1);
			entity.setDwxx(dwxxDao.getById(company.getId()));
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
			cal.setTime(d);
			entity.setNf(cal.get(Calendar.YEAR));
			entity.setYf(cal.get(Calendar.MONTH) + 1);
			entity.setDwxx(dwxxDao.getById(company.getId()));
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
			cal.setTime(d);
			entity.setNf(cal.get(Calendar.YEAR));
			entity.setYf(cal.get(Calendar.MONTH) + 1);
			entity.setDwxx(dwxxDao.getById(company.getId()));
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
		return entryYszkkxxz(d, company, data, ZBStatus.SUBMITTED);
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

	private final static String ncSqlYszkzlbh = 
			"	select 	"+	
			"	iui.unit_code,	"+	
			"	iui.unit_name,	"+	//单位名称
			"	imdx.m10080 wu,	"+	//五年以上
			"	imdx.m10020 siwu,	"+	//四至五年
			"	imdx.m10111 sansi,	"+	//三至四年
			"	imdx.m10097 ersan,	"+	//二至三年
			"	imdx.m10144 yier,	"+	//一至二年
			"	imdx.m10016 yi,	"+	//一年以内
			"	(imdx.m10080 + imdx.m10020 + imdx.m10111 + imdx.m10097 + imdx.m10144 + imdx.m10016) hj,	"+	//合计
			"	inputdate	"+	//日期
			"	from	"+	
			"	iufo_measure_data_xyy6hd5t imdx	"+	
			"	left join (select alone_id,code,inputdate,keyword2,keyword3,time_code,ts,ver from iufo_measure_pubdata ) imp on imdx.alone_id = imp.alone_id	"+	
			"	left join (select unit_id,unit_code,unit_name from iufo_unit_info) iui on imp.code = iui.unit_id	"+	
			"	where imp.ver = 510	%s "+	
			"		"+	//and iui.unit_name like '%特变电工股份有限公司新疆变压器厂（本部）%'
			"	order by unit_code,inputdate desc	";

	
	private final static String ncSqlZbm = 
			"	select 	"+	
			"	iui.unit_code,	"+	
			"	iui.unit_name,	"+	//单位名称
			"	imdx.m10063 yz,	"+	//应收账款原值
			"	imdx.m10151 hzzb,	"+	//坏账准备
			"	imdx.m10132 jz,	"+	//净值
			"	inputdate	"+	//日期
			"	from	"+	
			"	iufo_measure_data_xyy6hd5t imdx	"+	
			"	left join (select alone_id,code,inputdate,keyword2,keyword3,time_code,ts,ver from iufo_measure_pubdata ) imp on imdx.alone_id = imp.alone_id	"+	
			"	left join (select unit_id,unit_code,unit_name from iufo_unit_info) iui on imp.code = iui.unit_id	"+	
			"	where imp.ver = 510	%s "+	
			"		"+	//and iui.unit_name like '%特变电工股份有限公司新疆变压器厂（本部）%'
			"	order by unit_code,inputdate desc	";

	
	
	void mergeZmEntity(Calendar cal, ResultSet rs) throws SQLException{
		while (rs.next()) {
			
			String unitCode = String.valueOf(rs.getObject(1));
			CompanyType companyType = CompanyNCCode.getType(unitCode);
			Company comp = companyManager.getBMDBOrganization().getCompanyByType(companyType);
			int nf = cal.get(Calendar.YEAR);
			int yf = cal.get(Calendar.MONTH) + 1;
			YszkzmEntity entity = yszkzmDao.getByDate(DateUtil.toDate(cal), comp);
			if (null == entity){
				entity = new YszkzmEntity();
				entity.setDwxx(dwxxDao.getById(comp.getId()));
				entity.setNf(nf);
				entity.setYf(yf);
			}
			
			entity.setZmje(MathUtil.division(rs.getDouble(3), 10000d));
			entity.setHzzb(MathUtil.division(rs.getDouble(4), 10000d));
			entity.setYz(MathUtil.division(rs.getDouble(5), 10000d));
			yszkzmDao.merge(entity);
		}
	}
	
	
	
	@Override
	public void importZbmFromNC(Date d, List<Company> comps) {
		NCConnection connection = NCConnection.create();
		if (null != connection){
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			String whereSql = 
				" and iui.unit_code in (" + StringUtils.join(CompanyNCCode.toCodeList(comps).toArray(), ",") + ")" + 
				" and extract(year from to_date(inputdate,'yyyy-mm-dd')) =" + cal.get(Calendar.YEAR) + 
				" and extract(month from to_date(inputdate,'yyyy-mm-dd')) =" + (cal.get(Calendar.MONTH) + 1);
			Logger logger = Logger.getLogger("LOG-NC");
			logger.debug("应收账款管报 账面表");
			ResultSet rs = connection.query(String.format(ncSqlZbm, whereSql));
			if (null != rs){	
				try {
					mergeZmEntity(cal, rs);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}

	private void mergeZlbhEntity(Calendar cal, ResultSet rs) throws SQLException {
		while (rs.next()) {
			
			String unitCode = String.valueOf(rs.getObject(1));
			CompanyType companyType = CompanyNCCode.getType(unitCode);
			Company comp = companyManager.getBMDBOrganization().getCompanyByType(companyType);
			int nf = cal.get(Calendar.YEAR);
			int yf = cal.get(Calendar.MONTH) + 1;
			YszkZlEntity entity = yszkZlDao.getByDate(DateUtil.toDate(cal), comp);
			if (null == entity){
				entity = new YszkZlEntity();
				entity.setDwxx(dwxxDao.getById(comp.getId()));
				entity.setNf(nf);
				entity.setYf(yf);
			}
			
			entity.setZl5nys(MathUtil.division(rs.getDouble(3), 10000d));
			entity.setZl4z5n(MathUtil.division(rs.getDouble(4), 10000d));
			entity.setZl3z4n(MathUtil.division(rs.getDouble(5), 10000d));
			entity.setZl2z3n(MathUtil.division(rs.getDouble(6), 10000d));
			entity.setZl1z2n(MathUtil.division(rs.getDouble(7), 10000d));
			entity.setZl1nyn(MathUtil.division(rs.getDouble(8), 10000d));
			entity.setHj(MathUtil.division(rs.getDouble(9), 10000d));
			yszkZlDao.merge(entity);
		}
	}
	
	@Override
	public void importYszkzlbhFromNC(Date d, List<Company> yszkgbComps) {
		NCConnection connection = NCConnection.create();
		if (null != connection){
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			String whereSql = 
				" and iui.unit_code in (" + StringUtils.join(CompanyNCCode.toCodeList(yszkgbComps).toArray(), ",") + ")" + 
				" and extract(year from to_date(inputdate,'yyyy-mm-dd')) =" + cal.get(Calendar.YEAR) + 
				" and extract(month from to_date(inputdate,'yyyy-mm-dd')) =" + (cal.get(Calendar.MONTH) + 1);
			
			Logger logger = Logger.getLogger("LOG-NC");
			logger.debug("应收账款管报 账龄变化");
			ResultSet rs = connection.query(String.format(ncSqlYszkzlbh, whereSql));
			if (null != rs){	
				try {
					mergeZlbhEntity(cal, rs);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}


}
