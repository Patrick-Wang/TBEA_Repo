package com.tbea.ic.operation.service.chgb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		
		Double hjSyye = 0.0;
		Double hjByxz = 0.0;
		Double hjBycz = 0.0;
		Double hjQmye = 0.0;
		
		for (ChJykcEntity entity : entities){
			List<String> list = new ArrayList<String>();
			list.add(entity.getJykcxmEntity().getName());
			list.add("" + entity.getSyye());
			list.add("" + entity.getByxz());
			list.add("" + entity.getBycz());
			list.add("" + entity.getQmye());
			
			if (entity.getSyye() != null){
				hjSyye += entity.getSyye();
			}
			
			if (entity.getByxz() != null){
				hjByxz += entity.getByxz();
			}
			
			if (entity.getBycz() != null){
				hjBycz += entity.getBycz();
			}
			
			if (entity.getQmye() != null){
				hjQmye += entity.getQmye();
			}
			
			result.add(list);
		}
		
		List<String> list = new ArrayList<String>();

		list.add("合计");
		list.add("" + hjSyye);
		list.add("" + hjByxz);
		list.add("" + hjBycz);
		list.add("" + hjQmye);
		
		result.add(list);
		
		return result;
	}
}
