package com.tbea.ic.operation.service.chgb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Hashtable;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.chgb.nych.NychDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.nych.NychDao;
import com.tbea.ic.operation.model.dao.chgb.chxzqk.ChxzqkDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chxzqk.ChxzqkDao;
import com.tbea.ic.operation.model.dao.chgb.chzlbhqk.ChzlbhqkDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chzlbhqk.ChzlbhqkDao;
import com.tbea.ic.operation.model.dao.chgb.chjykc.ChJykcDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chjykc.ChJykcDao;

import javax.annotation.Resource;

import com.tbea.ic.operation.model.dao.chgb.chzm.ChZmDaoImpl;
import com.tbea.ic.operation.model.dao.chgb.chzm.ChZmDao;
import com.tbea.ic.operation.model.entity.chgb.ChZmEntity;
import com.tbea.ic.operation.model.entity.chgb.ChJykcEntity;
import com.tbea.ic.operation.service.chgb.ChgbService;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.identifier.chgb.jykcxm.JykcxmDao;
import com.tbea.ic.operation.model.dao.identifier.chgb.jykcxm.JykcxmDaoImpl;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.entity.identifier.chgb.JykcxmEntity;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.chgb.ChzlbhqkEntity;
import com.tbea.ic.operation.model.entity.chgb.ChxzqkEntity;
import com.tbea.ic.operation.model.entity.chgb.NychEntity;

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
		List<ChZmEntity> entities= chzmDao.getByDate(d, company);
		for (ChZmEntity entity : entities){
			List<String> list = new ArrayList<String>();
			list.add("" + entity.getZmje());
			list.add("" + entity.getHzzb());
			list.add("" + entity.getYz());
			result.add(list);
		}
		return result;
	}
	
	@Override
	public List<List<String>> getChjykcb(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		List<ChJykcEntity> entities= chJykcDao.getByDate(d, company);
		List<JykcxmEntity> jykcxmEntities = jykcxmDao.getXMMapping();
		
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
		List<ChzlbhqkEntity> entities= chzlbhqkDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
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
				Util.resize(result.get(result.size() - 1), 7);
			}
			cal.add(Calendar.MONTH, 1);
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
		list.add("" + Util.sum(new Double[]{
				entity.getYcl(),
				entity.getBcp(),
				entity.getSjkcsp(),
				entity.getYfhwkp(),
				entity.getQhfdyk(),
				entity.getQhpcyk(),
				entity.getWfhykp(),
				entity.getQt()}));
		return list;
	}
	
	@Override
	public List<List<String>> getChxzqk(Date d, Company company) {
		List<List<String>> result = new ArrayList<List<String>>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, -1);
		cal.add(Calendar.MONTH, 1);
		List<ChxzqkEntity> entities= chxzqkDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
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
				Util.resize(result.get(result.size() - 1), 9);
			}
			cal.add(Calendar.MONTH, 1);
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
		
		List<NychEntity> entities= nychDao.getByDate(new Date(cal.getTimeInMillis()), d, company);
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
				Util.resize(result.get(result.size() - 1), 6);
			}
			cal.add(Calendar.MONTH, 1);
		}
		
		NychEntity qcjyEntity = nychDao.getQCJYByDate(d, company);
		
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
}