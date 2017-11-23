package com.tbea.ic.operation.service.chgb;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
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
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.dao.chgb.chjykc.ChJykcDao;
import com.tbea.ic.operation.model.dao.chgb.chjykc.ChJykcDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chxzqk.ChxzqkDao;
import com.tbea.ic.operation.model.dao.chgb.chxzqk.ChxzqkDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chzlbhqk.ChzlbhqkDao;
import com.tbea.ic.operation.model.dao.chgb.chzlbhqk.ChzlbhqkDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chzm.ChZmDao;
import com.tbea.ic.operation.model.dao.chgb.chzm.ChZmDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.nych.NychDao;
import com.tbea.ic.operation.model.dao.chgb.nych.NychDaoImpl;
import com.tbea.ic.operation.model.dao.identifier.chgb.jykcxm.JykcxmDao;
import com.tbea.ic.operation.model.dao.identifier.chgb.jykcxm.JykcxmDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.entity.chgb.ChJykcEntity;
import com.tbea.ic.operation.model.entity.chgb.ChZmEntity;
import com.tbea.ic.operation.model.entity.chgb.ChxzqkEntity;
import com.tbea.ic.operation.model.entity.chgb.ChzlbhqkEntity;
import com.tbea.ic.operation.model.entity.chgb.NychEntity;
import com.tbea.ic.operation.model.entity.identifier.chgb.JykcxmEntity;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.service.util.nc.NCConnection;
import com.util.tools.DateUtil;
import com.util.tools.ListUtil;
import com.util.tools.MathUtil;

import net.sf.json.JSONArray;

@Service(ChgbServiceImpl.NAME)
@Transactional("transactionManager")
public class ChgbServiceImpl implements ChgbService {
	@Resource(name=NychDaoImpl.NAME)
	NychDao nychDao;

	@Resource(name=ChxzqkDaoImpl.NAME)
	ChxzqkDao chxzqkDao;

	@Resource(name=ChzlbhqkDaoImpl.NAME)
	ChzlbhqkDao chzlbhqkDao;

	@Resource(name=ChJykcDaoImpl.NAME)
	ChJykcDao chJykcDao;

	@Resource(name=ChZmDaoImpl.NAME)
	ChZmDao chzmDao;
	
	@Resource(name=JykcxmDaoImpl.NAME)
	JykcxmDao jykcxmDao;
	
	@Autowired
	DWXXDao dwxxDao;
	
	public final static String NAME = "ChgbServiceImpl";

	@Override
	public List<List<String>> getChzmb(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<ChZmEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = chzmDao.getByDate(d, company);
		}else{
			entities = chzmDao.getSumByDate(d, company.getSubCompanies());
		}
		
		for (ChZmEntity entity : entities){
			List<String> list = new ArrayList<String>();
			list.add("" + entity.getZmje());
			list.add("" + entity.getHzzb());
			list.add("" + entity.getYz());
			result.add(list);
		}
		if (result.isEmpty()) {
			List<String> list = new ArrayList<String>();
			list.add("null");
			list.add("null");
			list.add("null");
			result.add(list);
		}
		return result;
	}
	
	@Override
	public List<List<String>> getChjykcb(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<JykcxmEntity> jykcxmEntities = jykcxmDao.getXMMapping();
		
		List<ChJykcEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = chJykcDao.getByDate(d, company);
		}else{
			entities = chJykcDao.getSumByDate(d, company.getSubCompanies());
		}
		
		Double hjSyye = 0.0;
		Double hjByxz = 0.0;
		Double hjBycz = 0.0;
		Double hjQmye = 0.0;
		
		Boolean bHashjSyye = false;
		Boolean bHashhjByxz = false;
		Boolean bHashhjBycz = false;
		Boolean bHashhjQmye = false;
		
		
		for (int i = 0; i < jykcxmEntities.size(); i++) {
			
			Boolean bIsFind = false;
			List<String> list = new ArrayList<String>();
			
			for (ChJykcEntity entity : entities){
				
				if (entity.getJykcxmEntity().getId() == jykcxmEntities.get(i).getId()) {
					
					bIsFind = true;
					
					list.add(entity.getJykcxmEntity().getName());
					list.add("" + entity.getSyye());
					list.add("" + entity.getByxz());
					list.add("" + entity.getBycz());
					list.add("" + entity.getQmye());
					
					if (entity.getSyye() != null){
						hjSyye += entity.getSyye();
						bHashjSyye = true;
					}
					
					if (entity.getByxz() != null){
						hjByxz += entity.getByxz();
						bHashhjByxz = true;
					}
					
					if (entity.getBycz() != null){
						hjBycz += entity.getBycz();
						bHashhjBycz = true;
					}
					
					if (entity.getQmye() != null){
						hjQmye += entity.getQmye();
						bHashhjQmye = true;
					}

					entities.remove(entity);
					break;
				}
			}
			
			if (!bIsFind) {
				list.add(jykcxmEntities.get(i).getName());
				list.add("null");
				list.add("null");
				list.add("null");
				list.add("null");
			}

			result.add(list);
		}
			
		List<String> listHj = new ArrayList<String>();

		listHj.add("合计");
		
		if (bHashjSyye) {
			listHj.add("" + hjSyye);
		} else {
			listHj.add("null");
		}

		if (bHashhjByxz) {
			listHj.add("" + hjByxz);
		} else {
			listHj.add("null");
		}

		if (bHashhjBycz) {
			listHj.add("" + hjBycz);
		} else {
			listHj.add("null");
		}

		if (bHashhjQmye) {
			listHj.add("" + hjQmye);
		} else {
			listHj.add("null");
		}
		
		result.add(listHj);
		
		return result;
	}
	
	private List<String> toList(ChzlbhqkEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getZl5nys());
		list.add("" + entity.getZl4z5n());
		list.add("" + entity.getZl3z4n());
		list.add("" + entity.getZl2z3n());
		list.add("" + entity.getZl1z2n());
		list.add("" + entity.getZl1nyn());
		list.add("" + Util.sum(new Double[]{
				entity.getZl5nys(),
				entity.getZl4z5n(),
				entity.getZl3z4n(),
				entity.getZl2z3n(),
				entity.getZl1z2n(),
				entity.getZl1nyn()}));
		return list;
	}
	
	@Override
	public List<List<String>> getChzlbhqk(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);

		List<ChzlbhqkEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = chzlbhqkDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		}else{
			entities = chzlbhqkDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies());
		}
		
		
		for (int i = 0; i < 12; ++i){
			result.add(new ArrayList<String>());
			for (ChzlbhqkEntity entity : entities){
				if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
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
	public List<List<String>> getChzlbhqkDy(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		List<ChzlbhqkEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = chzlbhqkDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		}else{
			entities = chzlbhqkDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies());
		}
		
		
		result.add(new ArrayList<String>());
		for (ChzlbhqkEntity entity : entities){
			if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
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
	
	private List<String> toList(ChxzqkEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getYcl());
		list.add("" + entity.getBcp());
		list.add("" + entity.getSjkcsp());
		list.add("" + entity.getYfhwkp());
		list.add("" + entity.getQhfdyk());
		list.add("" + entity.getQhpcyk());
		list.add("" + entity.getWfhykp());
		list.add("" + entity.getQt());
		list.add("" + Util.minus(Util.sum(new Double[]{
											entity.getYcl(),
											entity.getBcp(),
											entity.getSjkcsp(),
											entity.getYfhwkp(),
											entity.getQhfdyk(),
											entity.getQhpcyk(),
											entity.getQt()}), 
								entity.getWfhykp()));
		return list;
	}
	
	@Override
	public List<List<String>> getChxzqk(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);

		List<ChxzqkEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = chxzqkDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		}else{
			entities = chxzqkDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies());
		}
		
		for (int i = 0; i < 12; ++i){
			result.add(new ArrayList<String>());
			for (ChxzqkEntity entity : entities){
				if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
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
	public List<List<String>> getChxzqkDy(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);

		List<ChxzqkEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = chxzqkDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		}else{
			entities = chxzqkDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies());
		}
		
		result.add(new ArrayList<String>());
		for (ChxzqkEntity entity : entities){
			if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
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
	
	private List<String> toList(NychEntity entity) {
		List<String> list = new ArrayList<String>();
		list.add("" + entity.getYcl());
		//list.add("" + entity.getYl());
		//list.add("" + entity.getBpbj());
		list.add("" + entity.getKcsp());
		list.add("" + entity.getSccbDpbtf());
		list.add("" + entity.getFcsp());
		list.add("" + entity.getDh());
		list.add("" + entity.getHj());
		return list;
	}
	
	@Override
	public List<List<String>> getChnych(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		List<NychEntity> entities = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			entities = nychDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
		}else{
			entities = nychDao.getSumByDate(new Date(cal.getTimeInMillis()), d, company.getSubCompanies());
		}
		
		
		for (int i = 0; i < 12; ++i){
			result.add(new ArrayList<String>());
			for (NychEntity entity : entities){
				if (entity.getNf() == cal.get(Calendar.YEAR) && entity.getYf() == cal.get(Calendar.MONTH) + 1){
					result.set(result.size() - 1, toList(entity));
					entities.remove(entity);
					break;
				}
			}
			if (result.get(result.size() - 1).isEmpty()){
				ListUtil.resize(result.get(result.size() - 1), 6);
			}
			cal.add(Calendar.MONTH, 1);
		}
		
		NychEntity qcjyEntity  = null;
		if (companyManager.getBMDBOrganization().owns(company)){
			qcjyEntity = nychDao.getQCJYByDate(d, company);
		}else{
			qcjyEntity = nychDao.getSumQCJYByDate(d, company.getSubCompanies());
		}
		
		if (qcjyEntity != null) {
			result.add(toList(qcjyEntity));
		}
		else {
			qcjyEntity = new NychEntity();
			result.add(toList(qcjyEntity));
		}
		
		return result;
	}
	
	@Override
	public List<List<String>> getChjykcbEntry(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<ChJykcEntity> entities= chJykcDao.getByDate(d, company);
		List<JykcxmEntity> jykcxmEntities = jykcxmDao.getXMMapping();
		
		for (int i = 0; i < jykcxmEntities.size(); i++) {
			
			Boolean bIsFind = false;
			List<String> list = new ArrayList<String>();
			
			for (ChJykcEntity entity : entities){
				
				if (entity.getJykcxmEntity().getId() == jykcxmEntities.get(i).getId()) {
					
					bIsFind = true;
					
					list.add(entity.getJykcxmEntity().getName());
					list.add("" + entity.getSyye());
					list.add("" + entity.getByxz());
					list.add("" + entity.getBycz());
					list.add("" + entity.getQmye());
					
					entities.remove(entity);
					break;
				}
			}
			
			if (!bIsFind) {
				list.add(jykcxmEntities.get(i).getName());
				list.add("null");
				list.add("null");
				list.add("null");
				list.add("null");
			}

			result.add(list);
		}
		
		return result;
	}

	ErrorCode entryChjykcb(Date d, Company company, JSONArray data, ZBStatus status) {
		ErrorCode err = ErrorCode.OK;

		List<JykcxmEntity> jykcxmEntities = jykcxmDao.getXMMapping();

		List<ChJykcEntity> entities = chJykcDao.getByDate(d, company);
		Hashtable<String, ChJykcEntity> hostEntities = new Hashtable<String, ChJykcEntity>();
		
		for (int i = 0; i < jykcxmEntities.size(); i++) {
			
			if (null != entities) {
				
				Boolean bIsFind = false;
				
				for (ChJykcEntity entity : entities){
					
					if (entity.getJykcxmEntity().getId() == jykcxmEntities.get(i).getId()) {
					
						bIsFind = true;					
						hostEntities.put(jykcxmEntities.get(i).getName(), entity);
						entities.remove(entity);
						break;
					}
				}
				
				if (!bIsFind) {
					ChJykcEntity newEntity = new ChJykcEntity();
					Calendar cal = Calendar.getInstance();
					cal.setTime(d);
					DWXX dwxx = new DWXX();
					dwxx.setId(company.getId());
					newEntity.setNf(cal.get(Calendar.YEAR));
					newEntity.setYf(cal.get(Calendar.MONTH) + 1);
					newEntity.setDwxx(dwxx);
					
					hostEntities.put(jykcxmEntities.get(i).getName(), newEntity);
				}
			} 
			else {
				
				ChJykcEntity newEntity = new ChJykcEntity();
				hostEntities.put(jykcxmEntities.get(i).getName(), newEntity);
			}
		}
		
		for (int i = 0; i < jykcxmEntities.size(); i++) {
			
			for (int j = 0; j < data.size(); j++) {
				
				if (data.getJSONArray(j).getString(0).equals(jykcxmEntities.get(i).getName())) {
					
					hostEntities.get(jykcxmEntities.get(i).getName()).setSyye(Util.toDoubleNull(data.getJSONArray(j).getString(1)));
					hostEntities.get(jykcxmEntities.get(i).getName()).setByxz(Util.toDoubleNull(data.getJSONArray(j).getString(2)));
					hostEntities.get(jykcxmEntities.get(i).getName()).setBycz(Util.toDoubleNull(data.getJSONArray(j).getString(3)));
					hostEntities.get(jykcxmEntities.get(i).getName()).setQmye(Util.toDoubleNull(data.getJSONArray(j).getString(4)));
					hostEntities.get(jykcxmEntities.get(i).getName()).setJykcxmEntity(jykcxmEntities.get(i));
					hostEntities.get(jykcxmEntities.get(i).getName()).setZt(status.ordinal());

					ChJykcEntity toEntity = hostEntities.get(jykcxmEntities.get(i).getName());
					
					chJykcDao.merge(toEntity);
				}				
			}
		}
		
		return err;
	}
	
	@Override
	public ErrorCode saveChjykcb(Date d, Company company, JSONArray data) {
		return entryChjykcb(d, company, data, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitChjykcb(Date d, Company company, JSONArray data) {
		return entryChjykcb(d, company, data, ZBStatus.SUBMITTED);
	}
	
	@Override
	public ZBStatus getChjykcbStatus(Date d, Company comp) {
		return ZBStatus.SAVED;
	}
	
	@Override
	public List<List<String>> getChzlbhqkEntry(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		ChzlbhqkEntity entity= chzlbhqkDao.getByDate(d, company);
		if (null != entity){
			List<String> list = toList(entity);
			result.add(list);
		}else{
			result.add(new ArrayList<>());
		}
		return result;
	}

	ErrorCode entryChzlbhqk(Date d, Company company, JSONArray data, ZBStatus status) {
		data = data.getJSONArray(0);
		ErrorCode err = ErrorCode.OK;
		ChzlbhqkEntity entity= chzlbhqkDao.getByDate(d, company);
		if (null == entity){
			entity = new ChzlbhqkEntity();
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			entity.setNf(cal.get(Calendar.YEAR));
			entity.setYf(cal.get(Calendar.MONTH) + 1);
			entity.setDwxx(dwxxDao.getById(company.getId()));
		}

		entity.setZt(status.ordinal());
		entity.setZl5nys(Util.toDoubleNull(data.getString(0)));
		entity.setZl4z5n(Util.toDoubleNull(data.getString(1)));
		entity.setZl3z4n(Util.toDoubleNull(data.getString(2)));
		entity.setZl2z3n(Util.toDoubleNull(data.getString(3)));
		entity.setZl1z2n(Util.toDoubleNull(data.getString(4)));
		entity.setZl1nyn(Util.toDoubleNull(data.getString(5)));
		chzlbhqkDao.merge(entity);
		return err;
	}
	
	@Override
	public ErrorCode saveChzlbhqk(Date d, Company company, JSONArray data) {
		return entryChzlbhqk(d, company, data, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitChzlbhqk(Date d, Company company, JSONArray data) {
		return entryChzlbhqk(d, company, data, ZBStatus.SUBMITTED);
	}
	
	@Override
	public ZBStatus getChzlbhqkStatus(Date d, Company comp) {
		return ZBStatus.SAVED;
	}
	
	@Override
	public List<List<String>> getChxzqkEntry(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		ChxzqkEntity entity= chxzqkDao.getByDate(d, company);
		if (null != entity){
			List<String> list = toList(entity);
			result.add(list);
		}else{
			result.add(new ArrayList<>());
		}
		return result;
	}

	ErrorCode entryChxzqk(Date d, Company company, JSONArray data, ZBStatus status) {
		data = data.getJSONArray(0);
		ErrorCode err = ErrorCode.OK;
		ChxzqkEntity entity= chxzqkDao.getByDate(d, company);
		if (null == entity){
			entity = new ChxzqkEntity();
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			entity.setNf(cal.get(Calendar.YEAR));
			entity.setYf(cal.get(Calendar.MONTH) + 1);
			entity.setDwxx(dwxxDao.getById(company.getId()));
		}

		entity.setZt(status.ordinal());
		entity.setYcl(Util.toDoubleNull(data.getString(0)));
		entity.setBcp(Util.toDoubleNull(data.getString(1)));
		entity.setSjkcsp(Util.toDoubleNull(data.getString(2)));
		entity.setYfhwkp(Util.toDoubleNull(data.getString(3)));
		entity.setQhfdyk(Util.toDoubleNull(data.getString(4)));
		entity.setQhpcyk(Util.toDoubleNull(data.getString(5)));
		entity.setWfhykp(Util.toDoubleNull(data.getString(6)));
		entity.setQt(Util.toDoubleNull(data.getString(7)));
		
		chxzqkDao.merge(entity);
		return err;
	}
	
	@Override
	public ErrorCode saveChxzqk(Date d, Company company, JSONArray data) {
		return entryChxzqk(d, company, data, ZBStatus.SAVED);
	}

	@Override
	public ErrorCode submitChxzqk(Date d, Company company, JSONArray data) {
		return entryChxzqk(d, company, data, ZBStatus.SUBMITTED);
	}
	
	@Override
	public ZBStatus getChxzqkStatus(Date d, Company comp) {
		return ZBStatus.SAVED;
	}
	
	
	//-----------------------------存货账面表-----------------------------------(账面原值为净额+减值）		
	private static String sqlZmb =  			
		"	select unit_code,	" +	
		"	       unit_name,	" +	
		"	       inputdate,	" +	
		"	       imda.m10083,	" +	//账面净额
		"	       imdo.m10000 	" +	//减值
		"	  from iufo_measure_data_aabf9rn7 imda	" +	
		"	  left join iufo_measure_data_osrehdc8 imdo	" +	
		"	    on imda.alone_id = imdo.alone_id	" +	
		"	  left join (select alone_id,	" +	
		"	                    code,	" +	
		"	                    inputdate,	" +	
		"	                    keyword2,	" +	
		"	                    keyword3,	" +	
		"	                    time_code,	" +	
		"	                    ts,	" +	
		"	                    ver	" +	
		"	               from iufo_measure_pubdata) imp	" +	
		"	    on imda.alone_id = imp.alone_id	" +	
		"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
		"	    on imp.code = iui.unit_id	" +	
		"	 where imp.ver = 510	%s ";	
				
				
		//-----------------------------能源存货-----------------------------------		
		private static String sqlNych =  			
			"	select unit_code,	" +	
			"	       unit_name,	" +	
			"	       inputdate,	" +	
			"	       imd8.m10006 yclnc,	" +	//原材料（年初）
			"	       imd8.m10010 kcspnc,	" +	//库存商品（年初）
			"	       imd8.m10002 dpbtfnc,	" +	//生产成本-待配比土方（年初）
			"	       imd8.m10008 fcspnc,	" +	//发出商品（年初）
			"	       imd8.m10000 dhnc,	" +	//低耗（年初）
			"	       imd8.m10004 hjnc,	" +	//合计（年初）
			"	       imd8.m10007 ycl,	" +	//原材料（期末）
			"	       imd8.m10011 kcsp,	" +	//库存商品（期末）
			"	       imd8.m10003 dpbtf,	" +	//生产成本-待配比土方（期末）
			"	       imd8.m10009 fcsp,	" +	//发出商品（期末）
			"	       imd8.m10001 dh,	" +	//低耗（期末）
			"	       imd8.m10005 hj 	" +	//合计（期末）
			"	  from iufo_measure_data_844a2dr9 imd8	" +	
			"	  left join (select alone_id,	" +	
			"	                    code,	" +	
			"	                    inputdate,	" +	
			"	                    keyword2,	" +	
			"	                    keyword3,	" +	
			"	                    time_code,	" +	
			"	                    ts,	" +	
			"	                    ver	" +	
			"	               from iufo_measure_pubdata) imp	" +	
			"	    on imd8.alone_id = imp.alone_id	" +	
			"	  left join (select unit_id, unit_code, unit_name from iufo_unit_info) iui	" +	
			"	    on imp.code = iui.unit_id	" +	
			"	 where imp.ver = 510	%s ";
		@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
		CompanyManager companyManager;
		@Override
		public void importZbmFromNC(Date d, List<Company> comps) {
			NCConnection connection = NCConnection.create();
			if (null != connection){
				Calendar cal = Calendar.getInstance();
				cal.setTime(d);
				String whereSql = 
					" and unit_code in (" + StringUtils.join(CompanyNCCode.toCodeList(comps).toArray(), ",") + ")" + 
					" and extract(year from to_date(inputdate,'yyyy-mm-dd')) =" + cal.get(Calendar.YEAR) + 
					" and extract(month from to_date(inputdate,'yyyy-mm-dd')) =" + (cal.get(Calendar.MONTH) + 1);

				Logger logger = Logger.getLogger("LOG-NC");
				logger.debug("存货账款管报  账面表");
				ResultSet rs = connection.query(String.format(sqlZmb, whereSql));
				if (rs != null){
					mergeZmb(cal, rs);
				}
			}
		}

		private void mergeZmb(Calendar cal, ResultSet rs) {
			try {
				
				int nf = cal.get(Calendar.YEAR);
				int yf = cal.get(Calendar.MONTH) + 1;
				Date d = DateUtil.toDate(cal);
				while (rs.next()) {

					String unitCode = String.valueOf(rs.getObject(1));
					CompanyType companyType = CompanyNCCode.getType(unitCode);
					Company comp = companyManager.getBMDBOrganization().getCompanyByType(companyType);
					
					List<ChZmEntity> entities = chzmDao.getByDate(d, comp);
					ChZmEntity entity = null;
					if (entities.isEmpty()){
						entity = new ChZmEntity();
						entity.setDwxx(dwxxDao.getById(comp.getId()));
						entity.setNf(nf);
						entity.setYf(yf);
					}else{
						entity = entities.get(0);
					}
					
					entity.setZmje(MathUtil.division(rs.getDouble(4), 10000d));
					entity.setHzzb(MathUtil.division(rs.getDouble(5), 10000d));
					entity.setYz(MathUtil.division(MathUtil.sum(rs.getDouble(4), rs.getDouble(5)), 10000d));
					
					chzmDao.merge(entity);
				}
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void importNychFromNC(Date d, List<Company> comps) {
			NCConnection connection = NCConnection.create();
			if (null != connection){
				Calendar cal = Calendar.getInstance();
				cal.setTime(d);
				String whereSql = 
					" and unit_code in (" + StringUtils.join(CompanyNCCode.toCodeList(comps).toArray(), ",") + ")" + 
					" and extract(year from to_date(inputdate,'yyyy-mm-dd')) =" + cal.get(Calendar.YEAR) + 
					" and extract(month from to_date(inputdate,'yyyy-mm-dd')) =" + (cal.get(Calendar.MONTH) + 1);
				
				Logger logger = Logger.getLogger("LOG-NC");
				logger.debug("存货账款管报  能源存货");
				
				ResultSet rs = connection.query(String.format(sqlNych, whereSql));
				if (rs != null){
					mergeNych(cal, rs);
				}
			}	
		}

		private void mergeNych(Calendar cal, ResultSet rs) {
			try {
				int nf = cal.get(Calendar.YEAR);
				int yf = cal.get(Calendar.MONTH) + 1;
				Date d = DateUtil.toDate(cal);
				while (rs.next()) {

					String unitCode = String.valueOf(rs.getObject(1));
					CompanyType companyType = CompanyNCCode.getType(unitCode);
					Company comp = companyManager.getBMDBOrganization().getCompanyByType(companyType);
					
					List<NychEntity> entities = nychDao.getByDate(d, d, comp);
					NychEntity entity = null;
					if (entities.isEmpty()){
						entity = new NychEntity();
						entity.setDwxx(dwxxDao.getById(comp.getId()));
						entity.setNf(nf);
						entity.setYf(yf);
					}else{
						entity = entities.get(0);
					}
					
					int base = 10;
					entity.setYcl(MathUtil.division(rs.getDouble(base++), 10000d));
					entity.setKcsp(MathUtil.division(rs.getDouble(base++), 10000d));
					entity.setSccbDpbtf(MathUtil.division(rs.getDouble(base++), 10000d));
					entity.setFcsp(MathUtil.division(rs.getDouble(base++), 10000d));
					entity.setDh(MathUtil.division(rs.getDouble(base++), 10000d));
					entity.setHj(MathUtil.division(rs.getDouble(base++), 10000d));
					nychDao.merge(entity);
					
					base = 4;
					entity = nychDao.getQCJYByDate(d, comp);
					if (null == entity){
						entity = new NychEntity();
						entity.setDwxx(dwxxDao.getById(comp.getId()));
						entity.setNf(nf);
						entity.setYf(13);
					}
					
					entity.setYcl(MathUtil.division(rs.getDouble(base++), 10000d));
					entity.setKcsp(MathUtil.division(rs.getDouble(base++), 10000d));
					entity.setSccbDpbtf(MathUtil.division(rs.getDouble(base++), 10000d));
					entity.setFcsp(MathUtil.division(rs.getDouble(base++), 10000d));
					entity.setDh(MathUtil.division(rs.getDouble(base++), 10000d));
					entity.setHj(MathUtil.division(rs.getDouble(base++), 10000d));
					nychDao.merge(entity);
				}
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	
}
